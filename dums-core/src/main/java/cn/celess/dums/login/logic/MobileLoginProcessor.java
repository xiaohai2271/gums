package cn.celess.dums.login.logic;


import cn.celess.dums.constants.UserConstant;
import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.exception.LoginFailedException;
import cn.celess.dums.login.AbstractLoginProcessor;
import cn.celess.dums.login.LoginProcessor;
import cn.celess.dums.vo.LoginUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    public Integer getLoginType() {
        return UserConstant.MOBILE_LOGIN;
    }

    @Override
    public LoginUserVO doLogin(UserLoginDto loginDto) throws LoginFailedException {
//        User user = new User();
//        // 判断登录失败次数
//        String loginFailedCountStr = RedisUtil.get(UserConstant.getCacheNameOfLoginFailedRecord(loginDto.getAccount(), loginDto.getPhone()));
//        if (!Strings.isNullOrEmpty(loginFailedCountStr) && Integer.parseInt(loginFailedCountStr) > ApplicationConfig.getInstance().maxLoginFailedTimes) {
//            throw new LoginFailedException(ResponseConstant.ACCOUNT_TEMP_LOCKED);
//        }
//
//        user.setPhone(loginDto.getPhone());
//        user = userDao.queryByUniqueKey(user);
//        if (user == null) {
//            throw new LoginFailedException(ResponseConstant.LOGIN_FAILED);
//        }
//
//        if (isUserLocked(user)) {
//            throw new LoginFailedException(ResponseConstant.ACCOUNT_TEMP_LOCKED);
//        }
//
//        // 校验手机验证码
//        String code = DataProcessorUtil.handlerAndRemoveVerifyCode(loginDto);
//        if (!Objects.equals(code, loginDto.getCode())) {
//            loginFailedAction(user);
//            throw new LoginFailedException(ResponseConstant.WRONG_MOBILE_VERIFY_CODE);
//        }
//        return loginSuccessAction(user, loginDto);
        return null;
    }
}
