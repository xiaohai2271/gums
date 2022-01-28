package cn.celess.gums.constants;

/**
 * 2021/11/12
 * TODO:
 *
 * @author 禾几海
 */
public class ApplicationConstant {
    public static final String APPLICATION_NAME = "gums";


    /**
     * ApplicationConfig 的本地配置
     */
    public static final String CONFIG_FILE = "app.config.json";
    /**
     * 配置文件路径,完整路径为:  ApplicationConfig.homeEnv + ApplicationConfig.configPath + ...
     */
    public static final String CONFIG_FILE_PATH = "/config/";
    /**
     * 配置文件，日志等文件存放的环境变量名，未定义环节变量则使用默认路径:
     * <pre>~/gums/</pre>
     */
    public static final String HOME_ENV = "DUMS_HOME";

    public static final String RUNTIME_CONFIG = "runtime.properties";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
}
