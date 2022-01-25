package cn.celess.gums.util;


import cn.celess.gums.common.enums.SmsCodeType;
import cn.celess.gums.dto.SmsDto;
import cn.celess.gums.dto.UserLoginDto;
import cn.celess.gums.dto.UserRegDto;
import cn.celess.gums.dto.UserResetPwdDto;
import cn.celess.gums.exception.ArgumentException;
import cn.celess.gums.exception.ArgumentMissingException;
import cn.celess.gums.response.ResponseConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * 2021/11/14
 *
 * @author 禾几海
 */
public class ValidUtil {

    /**
     * 校验图片验证码参数
     *
     * @param loginDto 信息
     */
    public static void checkImageCode(UserLoginDto loginDto) {
        if (StringUtils.isBlank(loginDto.getImgCode())) {
            throw new ArgumentMissingException(ResponseConstant.NO_IMAGE_CODE);
        }
    }

    public static void validRequestTokenArgs(Integer type) {
        if (Arrays.stream(SmsCodeType.values()).noneMatch(t -> Objects.equals(t.code, type))) {
            throw new ArgumentException(ResponseConstant.ARGUMENT_OF_TYPE_ERROR);
        }
    }

    public static void validRequestSmsArgs(SmsDto smsDto) {
        if (smsDto.getSmsCodeType() == null
                || StringUtils.isBlank(smsDto.getPhone())
                || StringUtils.isBlank(smsDto.getImgCode())
                || StringUtils.isBlank(smsDto.getToken())) {
            throw new ArgumentMissingException();
        }
        if (!RegexUtil.phoneMatch(smsDto.getPhone())) {
            throw new ArgumentException(ResponseConstant.PHONE_FORMAT_ERROR);
        }
    }

    public static void validRegistrationArgs(UserRegDto regDto) {
        if (StringUtils.isBlank(regDto.getUsername())) {
            throw new ArgumentMissingException("用户名");
        }
        if (StringUtils.isBlank(regDto.getPassword())) {
            throw new ArgumentMissingException("密码");
        }
        if (StringUtils.isBlank(regDto.getConfirmPassword())) {
            throw new ArgumentMissingException("确认密码");
        }
        if (StringUtils.isBlank(regDto.getPhone())) {
            throw new ArgumentMissingException("手机号");
        }
        if (StringUtils.isBlank(regDto.getSmsCode())) {
            throw new ArgumentMissingException("短信验证码");
        }
        if (!Objects.equals(regDto.getPassword(), regDto.getConfirmPassword())) {
            throw new ArgumentException(ResponseConstant.CONFIRM_PASSWORD_NOT_MATCH);
        }
        if (!RegexUtil.accountMatch(regDto.getUsername())) {
            throw new ArgumentException(ResponseConstant.ACCOUNT_FORMAT_ERROR);
        }
        if (!RegexUtil.passwordMatch(AesEncryptUtil.decrypt(regDto.getPassword(), regDto.getUsername()))) {
            throw new ArgumentException(ResponseConstant.PASSWORD_FORMAT_ERROR);
        }
        if (!RegexUtil.phoneMatch(regDto.getPhone())) {
            throw new ArgumentException(ResponseConstant.PHONE_FORMAT_ERROR);
        }
        if (!RegexUtil.verifyCodeMatch(regDto.getSmsCode())) {
            throw new ArgumentException(ResponseConstant.VERIFY_CODE_FORMAT_ERROR);
        }
    }

    public static void validResetPasswordArgs(UserResetPwdDto regDto) {
        if (StringUtils.isBlank(regDto.getPassword())
                || StringUtils.isBlank(regDto.getConfirmPassword())
                || StringUtils.isBlank(regDto.getPhone())
                || StringUtils.isBlank(regDto.getSmsCode())) {
            throw new ArgumentMissingException();
        }
        if (!Objects.equals(regDto.getPassword(), regDto.getConfirmPassword())) {
            throw new ArgumentException(ResponseConstant.CONFIRM_PASSWORD_NOT_MATCH);
        }
        if (!RegexUtil.phoneMatch(regDto.getPhone())) {
            throw new ArgumentException(ResponseConstant.PHONE_FORMAT_ERROR);
        }
        if (!RegexUtil.verifyCodeMatch(regDto.getSmsCode())) {
            throw new ArgumentException(ResponseConstant.VERIFY_CODE_FORMAT_ERROR);
        }
    }
}
