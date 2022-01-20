package cn.celess.dums.constants;


import cn.celess.dums.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 2021/11/12
 * TODO:
 *
 * @author 禾几海
 */
public class UserConstant {

    /**
     * session中存图形验证码的key
     */
    public static final String IMAGE_CODE_KEY = "image-verify-code";

    /**
     * 手机短信验证码缓存key
     * user:sms:+base64(手机号)
     */
    public static String getCacheNameOfMobileVerifyCode(String phone) {
        return String.format("user:sms:%s", Base64.getEncoder().encodeToString(phone.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 获取登录用户缓存的key
     * user:login:(id);
     */
    public static String getCacheNameOfUser(Integer id) {
        return String.format("user:login:%d", id);
    }

    /**
     * Session/缓存 中存储是否需要验证图形验证码的key
     * user:show:(id):imgcode
     *
     * <p>Value : true -> 需要图形验证码, false -> 不需要图形验证码</p>
     * <p>Value type : Boolean </p>
     */
    public static String getCacheNameOfDisplayableImgCode(String account) {
        return String.format("user:show:%s:imgcode", account);
    }

    /**
     * 缓存中存储用户登录失败的Key
     *
     * <b>若同一账户先后使用不同登录方式登录则分开计算</b>
     *
     * <p>Value : 登录失败的次数</p>
     * <p>Value type : Integer</p>
     */
    public static String getCacheNameOfLoginFailedRecord(User user) {
        String account = user.getUsername();
        String phone = user.getPhone();
        return getCacheNameOfLoginFailedRecord(account, phone);
    }

    /**
     * 缓存中存储用户登录失败的Key
     *
     * <b>若同一账户先后使用不同登录方式登录则分开计算</b>
     *
     * <p>Value : 登录失败的次数</p>
     * <p>Value type : Integer</p>
     *
     * @param account 账户
     * @param phone   手机号
     * @see UserConstant#getCacheNameOfLoginFailedRecord(User)
     */
    public static String getCacheNameOfLoginFailedRecord(String account, String phone) {
        account = StringUtils.isBlank(account) ? "" : account;
        phone = StringUtils.isBlank(phone) ? "" : phone;
        return String.format("user:login:%s:failed", Base64.getEncoder().encodeToString((account + phone).getBytes(StandardCharsets.UTF_8)));
    }

}
