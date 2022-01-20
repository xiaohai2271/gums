package cn.celess.dums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 2021/12/04
 *
 * @author 禾几海
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserResetPwdDto extends UserMobileVerifyDto {
    /**
     * 确认密码
     */
    private String confirmPassword;
}
