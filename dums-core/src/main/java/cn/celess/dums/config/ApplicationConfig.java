package cn.celess.dums.config;

import cn.celess.dums.page.Pageable;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * 2021/11/14
 * TODO:
 *
 * @author 禾几海
 */
public class ApplicationConfig {
    private ApplicationConfig() {
    }

    public synchronized static void init(String appConfigPath) {
        ApplicationConfigHolder.init(appConfigPath);
    }

    private static class ApplicationConfigHolder {
        private static ApplicationConfig instance = new ApplicationConfig();

        public synchronized static void init(String appConfigPath) {
            try {
                instance = new ObjectMapper().readValue(new File(appConfigPath), ApplicationConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ApplicationConfig getInstance() {
        return ApplicationConfigHolder.instance;
    }

    public Pageable defaultPageable = new Pageable(1, 10);
    /**
     * 最大错误登录次数
     * TODO (setting): 后台可调
     */
    public Integer maxLoginFailedTimes = 5;
    /**
     * 登陆失败，账户临时锁定时间
     * 10分钟 10*60s
     */
    public Integer temporaryLockTime = 10 * 60;
    /**
     * 登录失败超过多少次 需要验证图形验证码 ->针对 CUSTOM_LOGIN:
     * <br>
     * 0 < showImgCodedThreshold <= maxLoginFailedTimes
     */
    public Integer showImgCodedThreshold = 3;

    /**
     * 生成的token过期时间
     */
    public Integer tokenExpirationTime = 1;

    /**
     * 配置文件，日志等文件存放的环境变量名，未定义环节变量则使用默认路径:
     * <pre>~/dums/</pre>
     */
    public String homeEnv = "DUMS_HOME";


    public String runtimeConfig = "runtime.properties";


    /**
     * 登录用户信息保存时长（点击记住密码） 5天
     */
    public Long loginTokenExpirationTimeWithRememberMe = 5 * 24 * 60 * 60L;

    /**
     * 登录用户信息保存时长，两小时
     */
    public Long loginTokenExpirationTime = 2 * 60 * 60L;
}
