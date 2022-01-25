package cn.celess.gums.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2021/11/15
 * TODO:
 *
 * @author 禾几海
 */
public class RegexUtil {
    /**
     * 5-20位，
     * 只能是字母（大小写敏感），
     * 数字，下划线，
     * 不能以下划线开头和结尾
     *
     * @param account 账户
     * @return true/false
     */
    public static Boolean accountMatch(String account) {
        String pattern = "^[a-zA-Z\\d]\\w{3,19}[a-zA-Z\\d]$";
        return match(account, pattern);
    }

    /**
     * 电话号码匹配
     *
     * @param phone 电话号码
     * @return true/false
     */
    public static boolean phoneMatch(String phone) {
        String pattern = "^([1][3,4,5,6,7,8,9])\\d{9}$";
        return match(phone, pattern);
    }

    /**
     * 密码正则
     * 最短6位，最长16位 {6,16}
     * 可以包含小写大母 [a-z] 和大写字母 [A-Z]
     * 可以包含数字 [0-9]
     * 可以包含下划线 [ _ ] 和减号 [ - ]
     *
     * @param password 密码
     * @return true/false
     */
    public static boolean passwordMatch(String password) {
        String pattern = "^[\\w_-]{6,16}$";
        return match(password, pattern);
    }

    /**
     * 短信验证码
     *
     * @param code 短信验证码
     * @return true/false
     */
    public static boolean verifyCodeMatch(String code) {
        String pattern = "^[0-9]{6}$";
        return match(code, pattern);
    }

    /**
     * @param str     待匹配字符串
     * @param pattern 模式
     * @return true/false
     */
    public static boolean match(String str, String pattern) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank((pattern))) {
            return false;
        }
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
