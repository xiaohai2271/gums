package cn.celess.gums.response;

import lombok.Getter;

/**
 * 2021/11/12
 * TODO:
 *
 * @author 禾几海
 */
@Getter
public enum ResponseConstant {

    SUCCESS(0, "success"),
    FAILED(1, "fail"),

    MISSING_ARGUMENT(1000, "缺失请求参数"),
    ARGUMENT_FORMAT_ERROR(1100, "参数格式错误"),

    SEND_VERIFY_CODE_FIRST(1011, "请先获取手机验证码"),
    VERIFY_IMAGE_CODE_FIRST(1012, "请先验证图形验证码"),
    REQUEST_TOKEN_FIRST(1013, "token不存在或已过期"), // 请先获取token
    NO_IMAGE_CODE(1014, "图形验证码不能为空"),
    NO_PK(1014, "ID不能为空"),

    LOGIN_FAILED(2000, "用户名或者密码错误"),
    WRONG_MOBILE_VERIFY_CODE(2001, "短信验证码不正确"),
    WRONG_IMAGE_VERIFY_CODE(2002, "图形验证码不正确"),


    ACCOUNT_FORMAT_ERROR(2100, "账号格式错误"),
    PASSWORD_FORMAT_ERROR(2101, "密码格式错误"),
    PHONE_FORMAT_ERROR(2102, "手机号格式错误"),
    VERIFY_CODE_FORMAT_ERROR(2103, "验证码格式错误"),

    JWT_EXPIRED(2110, "Token过期"),
    JWT_MALFORMED(2111, "Token格式不对"),
    JWT_SIGNATURE(2112, "Token签名错误"),
    JWT_NOT_SUPPORT(2113, "不支持的Token"),

    ARGUMENT_ERROR(2200, "参数错误"),
    ARGUMENT_OF_TYPE_ERROR(2201, "类型参数错误"),
    CONFIRM_PASSWORD_NOT_MATCH(2202, "确认密码不匹配"),

    ACCOUNT_EXIST(2300, "账户已存在"),
    ACCOUNT_NOT_EXIST(2301, "账户不存在"),
    PHONE_HAS_REGISTERED(2302, "手机号已注册"),
    PHONE_NOT_REGISTERED(2303, "手机号未注册"),


    ACCOUNT_TEMP_LOCKED(2304, "多次登录失败，暂时锁定账户请稍后再试"),


    USER_NOT_LOGIN(2300, "用户未登录"),
    PERMISSION_DENIED(2301, "权限不足"),

    UNKNOWN_ERROR(9999, "未知错误"),
    LOGIN_TYPE_NOT_SUPPORT(2401, "该登录方式暂时无法使用");


    private final Integer status;
    private final String message;

    ResponseConstant(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
