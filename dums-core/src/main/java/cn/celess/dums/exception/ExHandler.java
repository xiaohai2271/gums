package cn.celess.dums.exception;

import cn.celess.dums.response.Response;
import cn.celess.dums.response.ResponseConstant;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>date: 2022/01/21</P>
 * <p>desc: 全局异常捕获</p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@ControllerAdvice
public class ExHandler {
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public static Response<Object> handleBaseGlobalException(CommonException e) {
        return e.getResponse() == null ? Response.error(ResponseConstant.UNKNOWN_ERROR) : e.getResponse();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public static Response<Object> handleException(Exception e) {
        e.printStackTrace();
        return Response.error(ResponseConstant.UNKNOWN_ERROR);
    }
}