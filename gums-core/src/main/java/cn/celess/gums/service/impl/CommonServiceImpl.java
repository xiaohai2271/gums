package cn.celess.gums.service.impl;


import cn.celess.gums.config.ApplicationConfig;
import cn.celess.gums.common.enums.SmsCodeType;
import cn.celess.gums.constants.UserConstant;
import cn.celess.gums.dto.SmsDto;
import cn.celess.gums.entity.User;
import cn.celess.gums.exception.ArgumentException;
import cn.celess.gums.exception.ArgumentMissingException;
import cn.celess.gums.exception.CommonException;
import cn.celess.gums.exception.LoginFailedException;
import cn.celess.gums.mapper.UserMapper;
import cn.celess.gums.response.ResponseConstant;
import cn.celess.gums.service.CommonService;
import cn.celess.gums.util.RedisUtil;
import cn.celess.gums.util.ValidUtil;
import cn.celess.gums.util.WebUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 2021/11/16
 * TODO:
 *
 * @author 禾几海
 */
@Slf4j
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String generateToken(Integer type) {
        ValidUtil.validRequestTokenArgs(type);
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        RedisUtil.setEx(token, type.toString(), ApplicationConfig.getInstance().tokenExpirationTime, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public void sendSmsCode(SmsDto smsDto) {
        ValidUtil.validRequestSmsArgs(smsDto);
        String cacheTokenValue = RedisUtil.get(smsDto.getToken());
        if (Strings.isNullOrEmpty(cacheTokenValue)) {
            throw new ArgumentMissingException(ResponseConstant.REQUEST_TOKEN_FIRST);
        }
        RedisUtil.delete(smsDto.getToken());
        if (Integer.parseInt(cacheTokenValue) != smsDto.getSmsCodeType().code) {
            throw new ArgumentException(ResponseConstant.ARGUMENT_OF_TYPE_ERROR);
        }

        String imageCode = (String) WebUtil.getHttpSession().getAttribute(UserConstant.IMAGE_CODE_KEY);
        // 移除session中的图形验证码
        WebUtil.getHttpSession().removeAttribute(UserConstant.IMAGE_CODE_KEY);
        if (Strings.isNullOrEmpty(imageCode)) {
            // message: 此处一般情况下不应该出现此问题，处于发送登录请求，一般会先获取验证码，那么此处不应该为空
            throw new ArgumentMissingException(ResponseConstant.VERIFY_IMAGE_CODE_FIRST);
        }
        // 验证图像验证码
        if (!imageCode.equalsIgnoreCase(smsDto.getImgCode())) {
            throw new LoginFailedException(ResponseConstant.WRONG_IMAGE_VERIFY_CODE);
        }
        // todo: 拆分

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, smsDto.getPhone()));
        // 注册，验证手机号是否已经注册
        if (Objects.equals(smsDto.getSmsCodeType(), SmsCodeType.SIGNUP_VERIFY_CODE)) {
            // 判断手机号是否已注册
            if (user != null) {
                throw new CommonException(ResponseConstant.PHONE_HAS_REGISTERED);
            }
        }
        // 找回密码，需要手机号已注册
        if (Objects.equals(smsDto.getSmsCodeType(), SmsCodeType.RESET_PASSWORD_VERIFY_CODE)
                || Objects.equals(smsDto.getSmsCodeType(), SmsCodeType.LOGIN_VERIFY_CODE)) {
            if (user == null) {
                throw new CommonException(ResponseConstant.PHONE_NOT_REGISTERED);
            }
        }

        // 获取/生成验证码
        String verifyCodeKey = UserConstant.getCacheNameOfMobileVerifyCode(smsDto.getPhone(), smsDto.getSmsCodeType());
        if (!RedisUtil.hasKey(verifyCodeKey)) {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(random.nextInt(10));
            }
            RedisUtil.setEx(verifyCodeKey, sb.toString(), 10, TimeUnit.MINUTES);
        }

        String code = RedisUtil.get(verifyCodeKey);

        // TODO:: sdk 发送短信验证码
        log.debug(code);
        // TODO:: 统计发送记录
    }
}
