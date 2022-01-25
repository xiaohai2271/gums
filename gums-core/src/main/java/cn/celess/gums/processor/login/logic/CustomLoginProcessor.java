package cn.celess.gums.processor.login.logic;


import cn.celess.gums.config.ApplicationConfig;
import cn.celess.gums.constants.UserConstant;
import cn.celess.gums.dto.UserLoginDto;
import cn.celess.gums.entity.User;
import cn.celess.gums.common.enums.LoginType;
import cn.celess.gums.exception.ArgumentException;
import cn.celess.gums.exception.ArgumentMissingException;
import cn.celess.gums.exception.CommonException;
import cn.celess.gums.exception.LoginFailedException;
import cn.celess.gums.processor.login.AbstractLoginProcessor;
import cn.celess.gums.processor.login.LoginProcessor;
import cn.celess.gums.response.ResponseConstant;
import cn.celess.gums.util.*;
import cn.celess.gums.vo.LoginUserVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 2021/11/14
 * TODO:
 *
 * @author 禾几海
 */
@Slf4j
@Component
public class CustomLoginProcessor extends AbstractLoginProcessor implements LoginProcessor {

    @Override
    public LoginType getLoginType() {
        return LoginType.CUSTOM_LOGIN;
    }

    @Override
    public LoginUserVO doLogin(UserLoginDto loginDto) throws LoginFailedException {
        User user;
        // 判断登录失败次数
        String loginFailedCountStr = RedisUtil.get(UserConstant.getCacheNameOfLoginFailedRecord(loginDto.getUsername(), loginDto.getPhone()));
        if (!StringUtils.isBlank(loginFailedCountStr) && Integer.parseInt(loginFailedCountStr) > ApplicationConfig.getInstance().maxLoginFailedTimes) {
            throw new LoginFailedException(ResponseConstant.ACCOUNT_TEMP_LOCKED);
        }

        // 校验图形验证码
        if (Boolean.TRUE.equals(WebUtil.getHttpSession().getAttribute(UserConstant.getCacheNameOfDisplayableImgCode(loginDto.getUsername())))) {
            validImageCode(loginDto);
        }

        // 查询账户信息
        user = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginDto.getUsername()));
        if (user == null) {
            throw new LoginFailedException(ResponseConstant.LOGIN_FAILED);
        }

        if (isUserLocked(user)) {
            throw new LoginFailedException(ResponseConstant.ACCOUNT_TEMP_LOCKED);
        }

        String encryptionPassword = DataProcessorUtil.handlerPassword(loginDto.getUsername(), loginDto.getPassword());
        if (!encryptionPassword.equals(user.getPassword())) {
            loginFailedAction(user);
            throw new LoginFailedException(ResponseConstant.LOGIN_FAILED);
        }
        return loginSuccessAction(user, loginDto);
    }

    @Override
    public Boolean enable() {
        return ApplicationConfig.getInstance().enableCustomLogin;
    }

    @Override
    public void checkArg(UserLoginDto loginDto) throws CommonException {
        LoginProcessor.super.checkArg(loginDto);
        if (StringUtils.isBlank(loginDto.getUsername())) {
            throw new ArgumentMissingException("用户名");
        }
        if (StringUtils.isBlank(loginDto.getPassword())) {
            throw new ArgumentMissingException("密码");
        }
        if (Boolean.TRUE.equals(WebUtil.getHttpSession().getAttribute(loginDto.getUsername()))) {
            ValidUtil.checkImageCode(loginDto);
        }
        if (!RegexUtil.accountMatch(loginDto.getUsername())) {
            throw new ArgumentException(ResponseConstant.ACCOUNT_FORMAT_ERROR);
        }
        if (!RegexUtil.passwordMatch(AesEncryptUtil.decrypt(loginDto.getPassword(), loginDto.getUsername()))) {
            throw new ArgumentException(ResponseConstant.PASSWORD_FORMAT_ERROR);
        }
    }
}
