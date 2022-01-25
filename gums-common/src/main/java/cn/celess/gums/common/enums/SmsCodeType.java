package cn.celess.gums.common.enums;

/**
 * <p>date: 2022/01/21</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
public enum SmsCodeType {

    // 登录时请求短信验证码
    LOGIN_VERIFY_CODE(1),
    // 注册
    SIGNUP_VERIFY_CODE(2),
    // 重置密码
    RESET_PASSWORD_VERIFY_CODE(3),
    // 验证手机号
    VERIFY_PHONE_VERIFY_CODE(4);

    public final Integer code;

    SmsCodeType(Integer code) {
        this.code = code;
    }
}
