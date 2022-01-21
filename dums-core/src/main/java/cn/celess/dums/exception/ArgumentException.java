package cn.celess.dums.exception;


import cn.celess.dums.response.ResponseConstant;

/**
 * 2021/12/29
 *
 * @author 禾几海
 */
public class ArgumentException extends CommonException {
    public ArgumentException() {
        super(ResponseConstant.ARGUMENT_ERROR);
    }

    public ArgumentException(ResponseConstant constant) {
        super(constant);
    }

    public ArgumentException(String message) {
        super(ResponseConstant.ARGUMENT_ERROR, "[" + message + "]错误");
    }
}
