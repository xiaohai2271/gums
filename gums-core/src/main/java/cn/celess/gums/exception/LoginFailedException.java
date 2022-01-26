package cn.celess.gums.exception;


import cn.celess.gums.common.response.ResponseConstant;

/**
 * 2021/12/29
 *
 * @author 禾几海
 */
public class LoginFailedException extends CommonException{
    public LoginFailedException() {
    }

    public LoginFailedException(ResponseConstant constant) {
        super(constant);
    }
}
