package cn.celess.dums.dto;

import cn.celess.dums.enums.LoginType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 2021/11/11
 * 登录用户dto
 *
 * @author 禾几海
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginDto extends UserMobileVerifyDto {

    /**
     * 图像验证码
     */
    private String imgCode;

    /**
     * 登录的类型
     */
    private LoginType loginType;

    /**
     * 是否 (记住设备 / 保持登录)
     */
    private boolean rememberMe = false;
}
