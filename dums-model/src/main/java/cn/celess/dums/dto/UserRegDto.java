package cn.celess.dums.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 2021/11/16
 * TODO:
 *
 * @author 禾几海
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegDto extends UserMobileVerifyDto {
    /**
     * 确认密码
     */
    private String confirmPassword;

    private Integer serviceId;
}
