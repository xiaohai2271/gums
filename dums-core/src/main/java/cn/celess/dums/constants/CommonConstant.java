package cn.celess.dums.constants;

/**
 * 2021/11/15
 * TODO:
 *
 * @author 禾几海
 */
public class CommonConstant {
    public enum TokenType {

        // 登录时请求短信验证码
        LOGIN_VERIFY_CODE(1),
        // 注册
        SIGNUP_VERIFY_CODE(2),
        // 重置密码
        RESET_PASSWORD_VERIFY_CODE(3),
        // 验证手机号
        VERIFY_PHONE_VERIFY_CODE(4);

        public final Integer code;

        TokenType(Integer code) {
            this.code = code;
        }
    }
}
