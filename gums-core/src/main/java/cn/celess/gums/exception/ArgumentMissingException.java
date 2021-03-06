package cn.celess.gums.exception;


import cn.celess.gums.common.response.ResponseConstant;

/**
 * 2021/12/29
 *
 * @author 禾几海
 */
public class ArgumentMissingException extends CommonException {
    public ArgumentMissingException() {
        super(ResponseConstant.MISSING_ARGUMENT);
    }

    public ArgumentMissingException(ResponseConstant constant) {
        super(constant);
    }

    public ArgumentMissingException(String message) {
        super(ResponseConstant.MISSING_ARGUMENT, "参数[" + message + "]缺失");
    }
}
