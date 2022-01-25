package cn.celess.gums.exception;

import cn.celess.gums.response.Response;
import cn.celess.gums.response.ResponseConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 2021/11/12
 * 全局异常类
 *
 * @author 禾几海
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends RuntimeException {
    private Response<Object> response;

    public CommonException() {
    }

    public CommonException(Response<Object> response) {
        super(response.getMessage());
        this.response = response;
    }

    public CommonException(String message, Response<Object> response) {
        super(message);
        this.response = response;
        this.response.setMessage(message);
    }

    public CommonException(ResponseConstant constant) {
        super(constant.getMessage());
        this.response = Response.error(constant);
    }

    public CommonException(ResponseConstant constant, String message) {
        super(message);
        this.response = Response.error(constant);
    }

}
