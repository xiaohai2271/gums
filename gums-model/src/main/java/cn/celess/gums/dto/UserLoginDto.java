package cn.celess.gums.dto;

import cn.celess.gums.enums.LoginType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 2021/11/11
 * 登录用户dto
 *
 * @author 禾几海
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
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
