package cn.celess.gums.config;

import cn.celess.gums.properties.GumsProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Configuration
@ComponentScan("cn.celess.gums")
@EnableCaching
@EnableConfigurationProperties({GumsProperties.class})
public class AutoConfiguration {
}
