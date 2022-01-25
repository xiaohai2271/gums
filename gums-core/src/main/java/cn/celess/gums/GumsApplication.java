package cn.celess.gums;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;

/**
 * <p>date: 2022/01/20</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Slf4j
@MapperScan("cn.celess.gums.mapper")
@SpringBootApplication
public class GumsApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GumsApplication.class)
                .main(SpringVersion.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        log.info("启动完成！");
    }
}
