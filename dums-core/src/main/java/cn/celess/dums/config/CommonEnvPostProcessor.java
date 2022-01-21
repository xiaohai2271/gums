package cn.celess.dums.config;

import cn.celess.dums.constants.ApplicationConstant;
import cn.celess.dums.util.EnvironmentUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * 2021/11/12
 * TODO:
 *
 * @author 禾几海
 */
@Component
public class CommonEnvPostProcessor implements EnvironmentPostProcessor, ApplicationListener<ApplicationEvent>, Ordered {
    public static final DeferredLog log = new DeferredLog();

    private static final String SOURCE_NAME = "localize";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {

        EnvironmentUtil.setEnvironment(configurableEnvironment);
        log.info("*************************Home*************************");
        log.info("加载本地配置文件--");
        //获取环境变量
        String defaultEnvHome = EnvironmentUtil.getProperties("user.home");
        String homeEnv = convertPathString(EnvironmentUtil.getEnv(ApplicationConstant.HOME_ENV, defaultEnvHome)
                + File.separator + ApplicationConstant.APPLICATION_NAME + File.separator);
        log.info("HomeEnv: \t\t\t" + homeEnv);

        String configPath = convertPathString(homeEnv + ApplicationConstant.CONFIG_FILE_PATH);

        initializeApplicationConfig(configPath);

        String runtimeConfigPath = convertPathString(configPath + ApplicationConstant.RUNTIME_CONFIG);
        log.info("runtimeConfigPath: " + runtimeConfigPath);
        try (InputStream input = new FileInputStream(runtimeConfigPath)) {
            Properties properties = new Properties();
            properties.load(input);
            PropertiesPropertySource propertySource = new PropertiesPropertySource(SOURCE_NAME, properties);
            configurableEnvironment.getPropertySources().addFirst(propertySource);
            log.info("成功加载本地配置文件:)");
        } catch (Exception e) {
            log.info("加载本地[" + runtimeConfigPath + "]的配置文件失败:(");
        }
        log.info("******************************************************");
    }

    private void initializeApplicationConfig(String configPath) {
        //初始化配置文件
        String appConfigPath = convertPathString(configPath + ApplicationConstant.CONFIG_FILE);
        log.info("AppConfigPath: \t" + appConfigPath);

        File configFile = new File(appConfigPath);

        if (!configFile.exists()) {
            log.info("未找到配置文件\t\t[" + appConfigPath + "]");
            return;
        }
        ApplicationConfig.init(appConfigPath);
    }

    public static String convertPathString(String path) {
        return path.replaceAll("[\\\\|/]+", Matcher.quoteReplacement(File.separator));
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationEvent event) {
        log.replayTo(CommonEnvPostProcessor.class);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}