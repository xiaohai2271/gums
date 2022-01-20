package cn.celess.dums.exception;


import cn.celess.dums.response.ResponseConstant;

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
