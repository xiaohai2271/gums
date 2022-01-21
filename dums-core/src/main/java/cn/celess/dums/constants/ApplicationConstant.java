package cn.celess.dums.constants;

/**
 * 2021/11/12
 * TODO:
 *
 * @author 禾几海
 */
public class ApplicationConstant {
    public static final String APPLICATION_NAME = "dums";

    /**
     * 请求头存放token的key
     */
    public static final String AUTH_HEADER_KEY = "Authorization";
    /**
     * Token 格式 Bearer token
     */
    public static final String AUTH_HEADER_VALUE_PREFIX = "Bearer";

    /**
     * ApplicationConfig 的本地配置
     */
    public static final String CONFIG_FILE = "app.config.json";
    /**
     * 配置文件路径,完整路径为:  ApplicationConfig.homeEnv + ApplicationConfig.configPath + ...
     */
    public static final String CONFIG_FILE_PATH = "/config/";

    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
}
