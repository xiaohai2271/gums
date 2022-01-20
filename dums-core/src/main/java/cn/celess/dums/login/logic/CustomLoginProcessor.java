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
public class CustomLoginProcessor extends AbstractLoginProcessor implements LoginProcessor {

    @Override
    public Integer getLoginType() {
        return UserConstant.CUSTOM_LOGIN;
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
//        // 校验图形验证码
//        if (Boolean.TRUE.equals(WebUtil.getHttpSession().getAttribute(UserConstant.getCacheNameOfDisplayableImgCode(loginDto.getAccount())))) {
//            validImageCode(loginDto);
//        }
//
//        // 账户密码登录
//        user.setUsername(loginDto.getAccount());
//        // 查询账户信息
//        user = userDao.queryByUniqueKey(user);
//        if (user == null) {
//            throw new LoginFailedException(ResponseConstant.LOGIN_FAILED);
//        }
//
//        if (isUserLocked(user)) {
//            throw new LoginFailedException(ResponseConstant.ACCOUNT_TEMP_LOCKED);
//        }
//
//        String encryptionPassword = DataProcessorUtil.handlerPassword(loginDto);
//        if (!encryptionPassword.equals(user.getPassword())) {
//            loginFailedAction(user);
//            throw new LoginFailedException(ResponseConstant.LOGIN_FAILED);
//        }
//        return loginSuccessAction(user, loginDto);
        return null;
    }
}
