package cn.celess.gums.service.impl;

import cn.celess.gums.constants.UserConstant;
import cn.celess.gums.convert.UserConvert;
import cn.celess.gums.dto.UserLoginDto;
import cn.celess.gums.dto.UserPageQueryDto;
import cn.celess.gums.dto.UserRegDto;
import cn.celess.gums.dto.UserResetPwdDto;
import cn.celess.gums.entity.User;
import cn.celess.gums.common.enums.SmsCodeType;
import cn.celess.gums.exception.ArgumentException;
import cn.celess.gums.exception.CommonException;
import cn.celess.gums.model.UserDetail;
import cn.celess.gums.page.Pageable;
import cn.celess.gums.processor.login.LoginProcessorFactory;
import cn.celess.gums.mapper.UserMapper;
import cn.celess.gums.page.PageVO;
import cn.celess.gums.response.ResponseConstant;
import cn.celess.gums.service.UserService;
import cn.celess.gums.util.*;
import cn.celess.gums.vo.CommonUserVO;
import cn.celess.gums.vo.LoginUserVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private LoginProcessorFactory loginProcessorFactory;


    @Override
    public LoginUserVO login(UserLoginDto loginDto) {
        return loginProcessorFactory.login(loginDto);
    }

    @Override
    public CommonUserVO registration(UserRegDto regDto) {
        // 参数校验
        ValidUtil.validRegistrationArgs(regDto);
        // 校验手机验证码
        String code = DataProcessorUtil.handlerAndRemoveVerifyCode(regDto, SmsCodeType.SIGNUP_VERIFY_CODE);
        if (!Objects.equals(code, regDto.getSmsCode())) {
            throw new CommonException(ResponseConstant.WRONG_MOBILE_VERIFY_CODE);
        }
        // 校验账号是否已经注册
        User user = baseMapper.selectOne(new LambdaQueryWrapper<>(User.class).eq(User::getUsername, regDto.getUsername()));
        if (user != null) {
            throw new CommonException(ResponseConstant.ACCOUNT_EXIST);
        }

        String encryptionPassword = DataProcessorUtil.handlerPassword(regDto.getUsername(), regDto.getPassword());

        User insertUser = new User();
        insertUser.setUsername(regDto.getUsername()).setPassword(encryptionPassword).setPhone(regDto.getPhone()).setPhoneStatus(true).setServiceId(regDto.getServiceId()).setCreateDt(LocalDateTime.now());
        baseMapper.insert(insertUser);

        return UserConvert.INSTANCE.toCommonUserVO(insertUser);
    }

    @Override
    public void logout() {
        UserDetail currentUser = UserContextUtil.getUser();
        if (currentUser == null) {
            return;
        }
        UserContextUtil.clear();
        RedisUtil.delete(UserConstant.getCacheNameOfUser(currentUser.getUser().getId()));
    }

    @Override
    public CommonUserVO getUserInfo() {
        UserDetail currentUser = UserContextUtil.getUser();
        if (currentUser == null) {
            throw new CommonException(ResponseConstant.USER_NOT_LOGIN);
        }
        CommonUserVO userVO = UserConvert.INSTANCE.toCommonUserVO(currentUser.getUser());
        userVO.setLonginHistory(currentUser.getLoginHistory());
        return userVO;
    }

    @Override
    public void resetPassword(UserResetPwdDto userRegDto) {
        ValidUtil.validResetPasswordArgs(userRegDto);
        // 校验手机验证码
        String code = DataProcessorUtil.handlerAndRemoveVerifyCode(userRegDto, SmsCodeType.RESET_PASSWORD_VERIFY_CODE);
        if (!Objects.equals(code, userRegDto.getSmsCode())) {
            throw new ArgumentException(ResponseConstant.WRONG_MOBILE_VERIFY_CODE);
        }

        // 更新用户信息
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, userRegDto.getPhone()));

        if (user == null) {
            // 此处不会出现这个错误，因为在发送验证码时就已有校验过手机号是否存在
            throw new CommonException(ResponseConstant.PHONE_NOT_REGISTERED);
        }

        // 校验密码是否格式
        if (!RegexUtil.passwordMatch(AesEncryptUtil.decrypt(userRegDto.getPassword(), user.getUsername()))) {
            throw new ArgumentException(ResponseConstant.PASSWORD_FORMAT_ERROR);
        }

        userRegDto.setUsername(user.getUsername());
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setPassword(DataProcessorUtil.handlerPassword(userRegDto.getUsername(), userRegDto.getPassword()));
        updateUser.setUpdateDt(LocalDateTime.now());
        baseMapper.updateById(updateUser);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CommonUserVO queryById(Integer id) {
        User user = baseMapper.selectById(id);
        return UserConvert.INSTANCE.toCommonUserVO(user);
    }


    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public PageVO<CommonUserVO> pageQuery(UserPageQueryDto pageQueryDto) {
        Pageable pageable = pageQueryDto.getPageable();
        IPage<CommonUserVO> page = baseMapper.pageQuery(new Page<>(pageable.getPageNum(), pageable.getPageSize()), pageQueryDto)
                .convert(UserConvert.INSTANCE::toCommonUserVO);
        PageVO<CommonUserVO> pageVO = new PageVO<>(page.getTotal(), page.getRecords());
        pageVO.setPageNum(pageable.getPageNum())
                .setPageSize(pageable.getPageSize());
        return pageVO;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public CommonUserVO insert(User user) {
        baseMapper.insert(user);
        return UserConvert.INSTANCE.toCommonUserVO(user);
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public CommonUserVO update(User user) {
        baseMapper.updateById(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return baseMapper.deleteById(id) > 0;
    }
}
