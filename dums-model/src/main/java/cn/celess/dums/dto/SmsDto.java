package cn.celess.dums.dto;

import cn.celess.dums.enums.SmsCodeType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 2021/11/15
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
public class SmsDto {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 图形验证码
     */
    private String imgCode;
    /**
     * token
     */
    private String token;
    /**
     * 业务类型
     */
    private SmsCodeType smsCodeType;
}
