package cn.celess.dums.service.impl;


import cn.celess.dums.config.ApplicationConfig;
import cn.celess.dums.constants.CommonConstant;
import cn.celess.dums.constants.UserConstant;
import cn.celess.dums.dto.SmsDto;
import cn.celess.dums.entity.User;
import cn.celess.dums.exception.ArgumentException;
import cn.celess.dums.exception.ArgumentMissingException;
import cn.celess.dums.exception.CommonException;
import cn.celess.dums.exception.LoginFailedException;
import cn.celess.dums.mapper.UserMapper;
import cn.celess.dums.response.ResponseConstant;
import cn.celess.dums.service.CommonService;
import cn.celess.dums.util.RedisUtil;
import cn.celess.dums.util.ValidUtil;
import cn.celess.dums.util.WebUtil;
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
        if (Integer.parseInt(cacheTokenValue) != smsDto.getType()) {
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

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, smsDto.getPhone()));
        // 注册，验证手机号是否已经注册
        if (Objects.equals(smsDto.getType(), CommonConstant.TokenType.SIGNUP_VERIFY_CODE.code)) {
            // 判断手机号是否已注册

            if (user != null) {
                throw new CommonException(ResponseConstant.PHONE_HAS_REGISTERED);
            }
        }
        // 找回密码，需要手机号已注册
        if (Objects.equals(smsDto.getType(), CommonConstant.TokenType.RESET_PASSWORD_VERIFY_CODE.code)
                || Objects.equals(smsDto.getType(), CommonConstant.TokenType.LOGIN_VERIFY_CODE.code)) {
            if (user == null) {
                throw new CommonException(ResponseConstant.PHONE_NOT_REGISTERED);
            }
        }

        // 获取/生成验证码
        if (!RedisUtil.hasKey(UserConstant.getCacheNameOfMobileVerifyCode(smsDto.getPhone()))) {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(random.nextInt(10));
            }
            RedisUtil.setEx(UserConstant.getCacheNameOfMobileVerifyCode(smsDto.getPhone()), sb.toString(), 10, TimeUnit.MINUTES);
        }

        String code = RedisUtil.get(UserConstant.getCacheNameOfMobileVerifyCode(smsDto.getPhone()));

        // TODO:: sdk 发送短信验证码
        log.debug(code);
        // TODO:: 统计发送记录
    }
}
