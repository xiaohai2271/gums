package cn.celess.gums.common.response;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 2021/11/12
 * Response 响应数据格式
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
@ToString
public class Response<T> {
    private Integer status;
    private String message;
    private T data;

    private Response() {
    }

    private Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    private Response(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * @param status 状态码
     * @param msg    失败信息
     * @param <T>    T
     * @return Response
     */
    public static <T> Response<T> of(int status, String msg) {
        return of(status, msg, null);
    }

    /**
     * @param constant 状态信息
     * @param <T>      T
     * @return Response
     */
    public static <T> Response<T> of(ResponseConstant constant) {
        return of(constant.getStatus(), constant.getMessage(), null);
    }

    /**
     * @param constant 状态信息
     * @param data     响应数据
     * @param <T>      T
     * @return Response
     */
    public static <T> Response<T> of(ResponseConstant constant, T data) {
        return of(constant.getStatus(), constant.getMessage(), data);
    }

    /**
     * @param status 状态码
     * @param msg    失败信息
     * @param data   响应数据
     * @param <T>    T
     * @return Response
     */
    public static <T> Response<T> of(int status, String msg, T data) {
        return new Response<>(status, msg, data);
    }

    /**
     * 创建成功的响应
     *
     * @param data 响应数据
     * @param <T>  T
     * @return Response
     */
    public static <T> Response<T> success(T data) {
        return of(ResponseConstant.SUCCESS, data);
    }

    /**
     * 创建失败的响应
     *
     * @param <T> T
     * @return Response
     */
    public static <T> Response<T> fail() {
        return of(ResponseConstant.FAILED);
    }

    /**
     * 创建失败的响应
     *
     * @param status 状态码
     * @param msg    失败信息
     * @param <T>    T
     * @return Response
     */
    public static <T> Response<T> error(int status, String msg) {
        return of(status, msg, null);
    }

    /**
     * 创建失败的响应
     *
     * @param constant 状态信息
     * @param <T>      T
     * @return Response
     */
    public static <T> Response<T> error(ResponseConstant constant) {
        return of(constant);
    }
}
