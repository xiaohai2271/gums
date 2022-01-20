package cn.celess.dums.exception;


import cn.celess.dums.response.ResponseConstant;

/**
 * 2021/12/29
 *
 * @author 禾几海
 */
public class ArgumentMissingException extends CommonException{
    public ArgumentMissingException() {
    }

    public ArgumentMissingException(ResponseConstant constant) {
        super(constant);
    }
}
