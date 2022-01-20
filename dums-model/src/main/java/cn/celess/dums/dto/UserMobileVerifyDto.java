package cn.celess.dums.dto;

import lombok.Data;

/**
 * 2021/11/17
 *
 * @author 禾几海
 */
@Data
public class UserMobileVerifyDto {
    /**
     * id
     */
    private Integer id;

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
    private String code;


}
