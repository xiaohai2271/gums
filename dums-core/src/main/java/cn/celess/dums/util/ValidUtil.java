package cn.celess.dums.util;


import cn.celess.dums.constants.CommonConstant;
import cn.celess.dums.constants.UserConstant;
import cn.celess.dums.dto.SmsDto;
import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.dto.UserRegDto;
import cn.celess.dums.dto.UserResetPwdDto;
import cn.celess.dums.enums.LoginType;
import cn.celess.dums.exception.ArgumentException;
import cn.celess.dums.exception.ArgumentMissingException;
import cn.celess.dums.response.ResponseConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 2021/11/14
 *
 * @author 禾几海
 */
public class ValidUtil {

    /**
     * 校验图片验证码参数
     * @param loginDto 信息
     */
    public static void checkImageCode(UserLoginDto loginDto) {
        if (StringUtils.isBlank(loginDto.getImgCode())) {
            throw new ArgumentMissingException(ResponseConstant.NO_IMAGE_CODE);
        }
    }

    public static void validRequestTokenArgs(Integer type) {
        if (Arrays.stream(CommonConstant.TokenType.values()).noneMatch(t -> Objects.equals(t.code, type))) {
            throw new ArgumentException(ResponseConstant.ARGUMENT_OF_TYPE_ERROR);
        }
    }

    public static void validRequestSmsArgs(SmsDto smsDto) {
        if (smsDto.getType() == null
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
        if (StringUtils.isBlank(regDto.getUsername())
                || StringUtils.isBlank(regDto.getPassword())
                || StringUtils.isBlank(regDto.getConfirmPassword())
                || StringUtils.isBlank(regDto.getPhone())
                || StringUtils.isBlank(regDto.getSmsCode())) {
            throw new ArgumentMissingException();
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
