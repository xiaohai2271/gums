package cn.celess.dums.login;

import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.exception.LoginFailedException;
import cn.celess.dums.vo.LoginUserVO;

/**
 * 2021/12/17
 *
 * @author 禾几海
 */
public interface LoginProcessor {
    /**
     * 获取登录类型
     * CUSTOM_LOGIN : 1
     * MOBILE_LOGIN : 1
     *
     * @return type
     * @see UserConstant#CUSTOM_LOGIN
     * @see UserConstant#MOBILE_LOGIN
     */
    Integer getLoginType();

    /**
     * 处理登录的逻辑
     *
     * @param loginDto 登录参数
     * @return 登录成功信息
     * @throws LoginFailedException 登录失败
     */
    LoginUserVO doLogin(UserLoginDto loginDto) throws LoginFailedException;


    /**
     * 此种登录方式是否可用，默认可用
     *
     * @return true: 可用...... false: 不可用
     */
    default Boolean enable() {
        return true;
    }
}
