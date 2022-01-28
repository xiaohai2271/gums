package cn.celess.gums.common.context;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 2021/12/17
 *
 * @author 禾几海
 */
@Component
public class GumsApplicationContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return applicationContext.getBean(beanName, requiredType);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        GumsApplicationContext.applicationContext = applicationContext;
    }
}
