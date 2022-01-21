package cn.celess.dums.login;


import cn.celess.dums.config.ApplicationConfig;
import cn.celess.dums.constants.UserConstant;
import cn.celess.dums.convert.UserConvert;
import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.entity.LoginHistory;
import cn.celess.dums.entity.Permission;
import cn.celess.dums.entity.User;
import cn.celess.dums.exception.ArgumentMissingException;
import cn.celess.dums.exception.LoginFailedException;
import cn.celess.dums.mapper.LoginHistoryMapper;
import cn.celess.dums.mapper.PermissionMapper;
import cn.celess.dums.mapper.RoleMapper;
import cn.celess.dums.mapper.UserMapper;
import cn.celess.dums.model.UserDetail;
import cn.celess.dums.response.ResponseConstant;
import cn.celess.dums.util.JwtUtil;
import cn.celess.dums.util.RedisUtil;
import cn.celess.dums.util.WebUtil;
import cn.celess.dums.vo.LoginUserVO;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 2021/11/14
 * 处理登录功能的处理类
 *
 * @author 禾几海
 */
@Slf4j
@Component
public abstract class AbstractLoginProcessor {

    @Resource
    protected UserMapper userDao;
    @Resource
    protected PermissionMapper permissionMapper;
    @Resource
    protected LoginHistoryMapper loginHistoryMapper;
    @Resource
    protected ObjectMapper objectMapper;

    /**
     * 校验图形验证码
     *
     * @param loginDto 登录信息
     * @throws ArgumentMissingException 未请求验证码
     * @throws LoginFailedException     登录失败
     */
    protected void validImageCode(UserLoginDto loginDto) throws ArgumentMissingException, LoginFailedException {
        String imageCode = (String) WebUtil.getHttpSession().getAttribute(UserConstant.IMAGE_CODE_KEY);

        // 移除session中的图形验证码
        WebUtil.getHttpSession().removeAttribute(UserConstant.IMAGE_CODE_KEY);

        if (StringUtils.isBlank(imageCode)) {
            // message: 此处一般情况下不应该出现此问题，处于发送登录请求，一般会先获取验证码，那么此处不应该为空
            throw new ArgumentMissingException(ResponseConstant.VERIFY_IMAGE_CODE_FIRST);
        }

        // 验证图像验证码
        if (!imageCode.equalsIgnoreCase(loginDto.getImgCode())) {
            throw new LoginFailedException(ResponseConstant.WRONG_IMAGE_VERIFY_CODE);
        }
    }

    /**
     * 检查用户是否被锁定
     *
     * @param user 用户信息
     * @return 是否被锁定（临时/长久）
     */
    protected Boolean isUserLocked(User user) {
        String key = UserConstant.getCacheNameOfLoginFailedRecord(user);
        String loginFailedCountStr = RedisUtil.get(key);
        return !StringUtils.isBlank(loginFailedCountStr) && Integer.parseInt(loginFailedCountStr) > ApplicationConfig.getInstance().maxLoginFailedTimes;
    }

    /**
     * 登录失败的数据善后逻辑
     *
     * @param u 数据库用户信息
     */
    protected void loginFailedAction(User u) {
        String key = UserConstant.getCacheNameOfLoginFailedRecord(u);
        String loginFailedCountStr = RedisUtil.get(key);
        if (StringUtils.isBlank(loginFailedCountStr)) {
            // 第一次登录失败，进行记录
            RedisUtil.setEx(key, String.valueOf(1), ApplicationConfig.getInstance().temporaryLockTime, TimeUnit.SECONDS);
        } else if (Integer.parseInt(loginFailedCountStr) > ApplicationConfig.getInstance().maxLoginFailedTimes) {
            // 账户被锁定，记录log
            log.info("用户{}:{}:{}登录错误次数达{}，现临时锁定{}秒", u.getId(), u.getUsername(), u.getPhone(), ApplicationConfig.getInstance().maxLoginFailedTimes, ApplicationConfig.getInstance().temporaryLockTime);
        } else {
            // 已有登录失败记录，但是未到锁定地步，增加错误次数,更新截至时间
            RedisUtil.incrBy(key, 1);
            RedisUtil.expire(key, ApplicationConfig.getInstance().temporaryLockTime, TimeUnit.SECONDS);

            if (Integer.parseInt(loginFailedCountStr) + 1 >= ApplicationConfig.getInstance().showImgCodedThreshold) {
                // 登录失败次数超过错误阈值，须进行图行验证码验证
                WebUtil.getHttpSession().setAttribute(UserConstant.getCacheNameOfDisplayableImgCode(u.getUsername()), Boolean.TRUE);
            }
        }
    }

    /**
     * 登录成功 数据处理逻辑
     *
     * @param user     用户信息
     * @param loginDto 请求数据
     * @return 用户信息模型
     */
    protected LoginUserVO loginSuccessAction(User user, UserLoginDto loginDto) {
        LoginUserVO userVO = UserConvert.INSTANCE.toVo(user);

        WebUtil.getHttpSession().removeAttribute(UserConstant.getCacheNameOfDisplayableImgCode(user.getUsername()));

//        // 查用户权限
        List<Permission> permissionList = new ArrayList<>();
        user.getRoles().forEach(role -> {
            List<Permission> permissionByRoleId = permissionMapper.getPermissionByRoleId(role.getId());
            role.setPermissions(permissionByRoleId);
            permissionList.addAll(permissionByRoleId);
        });

        UserDetail userDetail = new UserDetail().setUser(user)
                .setRoles(user.getRoles())
                .setAllPermissions(permissionList);

        // 写缓存
        try {
            RedisUtil.setEx(
                    UserConstant.getCacheNameOfUser(user.getId()),
                    objectMapper.writeValueAsString(userDetail),
                    loginDto.isRememberMe() ?
                            ApplicationConfig.getInstance().loginTokenExpirationTimeWithRememberMe
                            : ApplicationConfig.getInstance().loginTokenExpirationTime,
                    TimeUnit.SECONDS
            );
        } catch (JsonProcessingException e) {
            log.info("写入缓存数据失败: [{}]", user);
            e.printStackTrace();
        }

//        UserContextUtil.setUser(userDetail);

        RedisUtil.delete(UserConstant.getCacheNameOfLoginFailedRecord(new User().setUsername(user.getUsername())));
        RedisUtil.delete(UserConstant.getCacheNameOfLoginFailedRecord(new User().setUsername(user.getPhone())));
        // 更新最后登录日期
        User updateDt = new User()
                .setId(user.getId());

        LoginHistory history = null;
        Page<LoginHistory> loginHistoryPage = loginHistoryMapper.selectPage(new Page<>(1, 1), new LambdaQueryWrapper<LoginHistory>()
                .eq(LoginHistory::getUserId, user.getId())
                .orderByDesc(LoginHistory::getCreateDt)
        );
        if (loginHistoryPage.getRecords().size() > 0) {
            history = loginHistoryPage.getRecords().get(0);
        }
        history = Optional.ofNullable(history).orElse(new LoginHistory());
        LoginHistory loginHistory = new LoginHistory()
                .setUserId(user.getId())
                .setCreateDt(LocalDateTime.now())
                .setServiceId(-1); // todo:

        if (loginHistoryMapper.insert(loginHistory) == 0) {
            log.info("更新登录日期失败，uid: {}, from: {} to:{} ", user.getId(), history.getCreateDt(), loginHistory.getCreateDt());
        }

        userVO.setLonginHistory(history);
        // 生成Token
        String token = JwtUtil.generateToken(user, loginDto.isRememberMe());
        userVO.setToken(token);
        return userVO;
    }

}
