package cn.celess.dums;

import lombok.extern.slf4j.Slf4j;
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
@SpringBootApplication
@Slf4j
public class DumsApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DumsApplication.class)
                .main(SpringVersion.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        log.info("启动完成！");
    }
}
