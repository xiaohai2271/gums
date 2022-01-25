package cn.celess.gums.enums;

/**
 * <p>date: 2022/01/20</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
public enum LoginType {
    //  常规登录  账户+密码
    CUSTOM_LOGIN(1, "常规登录"),
    MOBILE_LOGIN(2, "手机号登录"),
    EMAIL_LOGIN(3, "邮箱登录");

    private final Integer code;
    private final String name;

    LoginType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
