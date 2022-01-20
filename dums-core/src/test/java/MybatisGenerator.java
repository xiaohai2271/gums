import cn.celess.dums.DumsApplication;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>date: 2022/01/20</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
public class MybatisGenerator {
    public static final String URL = "jdbc:postgresql://localhost:5432/dums";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "zhenghai";
    public static final String[] TABLE_NAME = {};

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        Map<OutputFile, String> outputFileStringMap = new HashMap<>();
        outputFileStringMap.put(OutputFile.mapperXml, projectPath + "/dums-core/src/main/resources/mapper");
        outputFileStringMap.put(OutputFile.entity, projectPath + "/dums-model/src/main/java/cn/celess/dums/entity");


        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("禾几海") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 禁止打开目录
                            .commentDate("yyyy/MM/dd")
                            .outputDir(projectPath + "/dums-core/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.celess.dums") // 设置父包名
                            .pathInfo(outputFileStringMap); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok() // 开启lombok
                            .enableChainModel(); // 开启链式模式
                    builder.serviceBuilder().formatServiceFileName("%sService")
                                    .formatServiceImplFileName("%sServiceImpl");
                    builder.addInclude(TABLE_NAME) // 设置需要生成的表名
                            .addTablePrefix("dums_"); // 设置过滤表前缀
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER);
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
