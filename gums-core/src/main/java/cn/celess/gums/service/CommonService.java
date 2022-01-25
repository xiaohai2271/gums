package cn.celess.gums.service;

import cn.celess.gums.dto.SmsDto;

/**
 * 2021/11/16
 * TODO:
 *
 * @author 禾几海
 */
public interface CommonService {
    /**
     * 生成token
     *
     * @return token
     * @param type
     */
    String generateToken(Integer type);

    /**
     * 发送短信验证码
     *
     * @param smsDto 短信参数
     */
    void sendSmsCode(SmsDto smsDto);
}
