package cn.celess.gums.util;

import cn.celess.gums.config.ApplicationConfig;
import cn.celess.gums.constants.ApplicationConstant;
import cn.celess.gums.common.enums.SmsCodeType;
import cn.celess.gums.constants.UserConstant;
import cn.celess.gums.dto.UserLoginDto;
import cn.celess.gums.dto.UserMobileVerifyDto;
import cn.celess.gums.exception.ArgumentMissingException;
import cn.celess.gums.exception.CommonException;
import cn.celess.gums.page.Pageable;
import cn.celess.gums.response.ResponseConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * 2021/11/14
 * TODO:
 *
 * @author 禾几海
 */
@Slf4j
public class DataProcessorUtil {
    public static void handlerLoginData(UserLoginDto loginDto) {
        trimProp(loginDto);
    }


    public static String handlerPassword(String account, String password) {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            throw new ArgumentMissingException();
        }
        // TODO: 账户
        // 字符串处理
        account = account.toLowerCase(Locale.ROOT);
        // 得到原始密码
        String originPassword = AesEncryptUtil.decrypt(password, account);
        if (StringUtils.isBlank(originPassword)) {
            return "";
        }
        // MD5(账号+原始密码+应用盐)
        return DigestUtils.md5DigestAsHex((account + originPassword + ApplicationConstant.APPLICATION_NAME).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 校验并删除手机验证码
     *
     * @param userDto 包含验证码/手机号的用户信息
     * @return 验证码
     * @throws CommonException
     */
    public static String handlerAndRemoveVerifyCode(UserMobileVerifyDto userDto, SmsCodeType codeType) throws CommonException {
        // 校验手机验证码
        String verifyCodeKey = UserConstant.getCacheNameOfMobileVerifyCode(userDto.getPhone(), codeType);
        String phoneVerifyCode = RedisUtil.get(verifyCodeKey);
        if (StringUtils.isBlank(phoneVerifyCode)) {
            throw new CommonException(ResponseConstant.SEND_VERIFY_CODE_FIRST);
        }
        // 删除验证码缓存
        RedisUtil.delete(verifyCodeKey);
        return phoneVerifyCode;
    }

    public static Pageable handlePageable(Pageable pageable) {
        if (pageable == null) {
            return ApplicationConfig.getInstance().defaultPageable;
        }
        if (pageable.getPageNum() == null) {
            pageable.setPageNum(ApplicationConfig.getInstance().defaultPageable.getPageNum());
        }
        if (pageable.getPageSize() == null) {
            pageable.setPageSize(ApplicationConfig.getInstance().defaultPageable.getPageSize());
        }
        return pageable;
    }

    public static <T> void trimProp(T t) {
        Class<?> aClass = t.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.getType().equals(String.class) && field.get(t) != null) {
                    field.set(t, ((String) field.get(t)).trim());
                }
            }
        } catch (IllegalAccessException e) {
            log.debug("tirm string of {} failed", t.getClass());
            e.printStackTrace();
        }
    }
}
