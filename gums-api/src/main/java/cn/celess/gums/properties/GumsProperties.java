package cn.celess.gums.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Component
@ConfigurationProperties(prefix = "gums")
@Data
public class GumsProperties {
    /**
     * 服务编号
     */
    private Integer serviceId;

    /**
     * 服务名称
     */
    private String serviceName;
}
