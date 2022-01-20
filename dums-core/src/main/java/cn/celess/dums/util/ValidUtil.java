package cn.celess.dums.util;


import cn.celess.dums.constants.CommonConstant;
import cn.celess.dums.constants.UserConstant;
import cn.celess.dums.dto.SmsDto;
import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.dto.UserRegDto;
import cn.celess.dums.dto.UserResetPwdDto;
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

    public static void validLoginArgs(UserLoginDto loginDto) {
        // 校验图片验证码参数
        Consumer<UserLoginDto> checkImageCode = (dto) -> {
            if (StringUtils.isBlank(dto.getImgCode())) {
                throw new ArgumentMissingException(ResponseConstant.NO_IMAGE_CODE);
            }
        };

        if (loginDto.getType() == null) {
            throw new ArgumentMissingException();
        }
        // CUSTOM_LOGIN: 账户 + 密码 + <图形验证码>
        if (Objects.equals(loginDto.getType(), UserConstant.CUSTOM_LOGIN)) {
            if (StringUtils.isBlank(loginDto.getAccount()) || StringUtils.isBlank(loginDto.getPassword())) {
                throw new ArgumentMissingException();
            }
            if (Boolean.TRUE.equals(WebUtil.getHttpSession().getAttribute(loginDto.getAccount()))) {
                checkImageCode.accept(loginDto);
            }
            if (!RegexUtil.accountMatch(loginDto.getAccount())) {
                throw new ArgumentException(ResponseConstant.ACCOUNT_FORMAT_ERROR);
            }
            if (!RegexUtil.passwordMatch(AesEncryptUtil.decrypt(loginDto.getPassword(), loginDto.getAccount()))) {
                throw new ArgumentException(ResponseConstant.PASSWORD_FORMAT_ERROR);
            }
        }
        // CUSTOM_LOGIN: 手机号 + 手机验证码 + <图形验证码>
        if (Objects.equals(loginDto.getType(), UserConstant.MOBILE_LOGIN)) {
            if (StringUtils.isBlank(loginDto.getPhone()) || StringUtils.isBlank(loginDto.getCode())) {
                throw new ArgumentMissingException();
            }
//            checkImageCode.accept(loginDto);
            if (!RegexUtil.phoneMatch(loginDto.getPhone())) {
                throw new ArgumentException(ResponseConstant.PHONE_FORMAT_ERROR);
            }
            if (!RegexUtil.verifyCodeMatch(loginDto.getCode())) {
                throw new ArgumentException(ResponseConstant.VERIFY_CODE_FORMAT_ERROR);
            }
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
        if (StringUtils.isBlank(regDto.getAccount())
                || StringUtils.isBlank(regDto.getPassword())
                || StringUtils.isBlank(regDto.getConfirmPassword())
                || StringUtils.isBlank(regDto.getPhone())
                || StringUtils.isBlank(regDto.getCode())) {
            throw new ArgumentMissingException();
        }
        if (!Objects.equals(regDto.getPassword(), regDto.getConfirmPassword())) {
            throw new ArgumentException(ResponseConstant.CONFIRM_PASSWORD_NOT_MATCH);
        }
        if (!RegexUtil.accountMatch(regDto.getAccount())) {
            throw new ArgumentException(ResponseConstant.ACCOUNT_FORMAT_ERROR);
        }
        if (!RegexUtil.passwordMatch(AesEncryptUtil.decrypt(regDto.getPassword(), regDto.getAccount()))) {
            throw new ArgumentException(ResponseConstant.PASSWORD_FORMAT_ERROR);
        }
        if (!RegexUtil.phoneMatch(regDto.getPhone())) {
            throw new ArgumentException(ResponseConstant.PHONE_FORMAT_ERROR);
        }
        if (!RegexUtil.verifyCodeMatch(regDto.getCode())) {
            throw new ArgumentException(ResponseConstant.VERIFY_CODE_FORMAT_ERROR);
        }
    }

    public static void validResetPasswordArgs(UserResetPwdDto regDto) {
        if (StringUtils.isBlank(regDto.getPassword())
                || StringUtils.isBlank(regDto.getConfirmPassword())
                || StringUtils.isBlank(regDto.getPhone())
                || StringUtils.isBlank(regDto.getCode())) {
            throw new ArgumentMissingException();
        }
        if (!Objects.equals(regDto.getPassword(), regDto.getConfirmPassword())) {
            throw new ArgumentException(ResponseConstant.CONFIRM_PASSWORD_NOT_MATCH);
        }
        if (!RegexUtil.phoneMatch(regDto.getPhone())) {
            throw new ArgumentException(ResponseConstant.PHONE_FORMAT_ERROR);
        }
        if (!RegexUtil.verifyCodeMatch(regDto.getCode())) {
            throw new ArgumentException(ResponseConstant.VERIFY_CODE_FORMAT_ERROR);
        }
    }
}
