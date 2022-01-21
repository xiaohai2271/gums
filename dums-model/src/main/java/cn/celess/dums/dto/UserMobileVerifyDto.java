package cn.celess.dums.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 2021/11/17
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
public class UserMobileVerifyDto {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 账户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 短信验证码
     */
    private String smsCode;

}
