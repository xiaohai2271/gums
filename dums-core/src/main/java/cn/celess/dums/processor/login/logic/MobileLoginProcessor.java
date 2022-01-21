package cn.celess.dums.processor.login.logic;


import cn.celess.dums.config.ApplicationConfig;
import cn.celess.dums.constants.UserConstant;
import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.entity.User;
import cn.celess.dums.enums.LoginType;
import cn.celess.dums.enums.SmsCodeType;
import cn.celess.dums.exception.ArgumentException;
import cn.celess.dums.exception.ArgumentMissingException;
import cn.celess.dums.exception.CommonException;
import cn.celess.dums.exception.LoginFailedException;
import cn.celess.dums.processor.login.AbstractLoginProcessor;
import cn.celess.dums.processor.login.LoginProcessor;
import cn.celess.dums.response.ResponseConstant;
import cn.celess.dums.util.DataProcessorUtil;
import cn.celess.dums.util.RedisUtil;
import cn.celess.dums.util.RegexUtil;
import cn.celess.dums.vo.LoginUserVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 2021/11/14
 * TODO:
 *
 * @author 禾几海
 */
@Slf4j
@Component
public class MobileLoginProcessor extends AbstractLoginProcessor implements LoginProcessor {
    @Override
    public LoginType getLoginType() {
        return LoginType.MOBILE_LOGIN;
    }

    @Override
    public LoginUserVO doLogin(UserLoginDto loginDto) throws LoginFailedException {
        User user = new User();
        // 判断登录失败次数
        String loginFailedCountStr = RedisUtil.get(UserConstant.getCacheNameOfLoginFailedRecord(loginDto.getUsername(), loginDto.getPhone()));
        if (!Strings.isNullOrEmpty(loginFailedCountStr) && Integer.parseInt(loginFailedCountStr) > ApplicationConfig.getInstance().maxLoginFailedTimes) {
            throw new LoginFailedException(ResponseConstant.ACCOUNT_TEMP_LOCKED);
        }

        user.setPhone(loginDto.getPhone());
        // 查询账户信息
        user = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, loginDto.getPhone()));

        if (user == null) {
            throw new LoginFailedException(ResponseConstant.PHONE_NOT_REGISTERED);
        }

        if (isUserLocked(user)) {
            throw new LoginFailedException(ResponseConstant.ACCOUNT_TEMP_LOCKED);
        }
        // 校验手机验证码
        String code = DataProcessorUtil.handlerAndRemoveVerifyCode(loginDto, SmsCodeType.LOGIN_VERIFY_CODE);
        if (!Objects.equals(code, loginDto.getSmsCode())) {
            loginFailedAction(user);
            throw new LoginFailedException(ResponseConstant.WRONG_MOBILE_VERIFY_CODE);
        }
        return loginSuccessAction(user, loginDto);
    }

    @Override
    public Boolean enable() {
        return ApplicationConfig.getInstance().enableMobileLogin;
    }

    @Override
    public void checkArg(UserLoginDto loginDto) throws CommonException {
        LoginProcessor.super.checkArg(loginDto);
        if (StringUtils.isBlank(loginDto.getPhone())) {
            throw new ArgumentMissingException("手机号");
        }
        if (StringUtils.isBlank(loginDto.getSmsCode())) {
            throw new ArgumentMissingException("短信验证码");
        }
        if (!RegexUtil.phoneMatch(loginDto.getPhone())) {
            throw new ArgumentException(ResponseConstant.PHONE_FORMAT_ERROR);
        }
        if (!RegexUtil.verifyCodeMatch(loginDto.getSmsCode())) {
            throw new ArgumentException(ResponseConstant.VERIFY_CODE_FORMAT_ERROR);
        }
    }
}
