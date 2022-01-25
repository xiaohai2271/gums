package cn.celess.gums.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p>date: 2022/01/21</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {


    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        /* 单页分页条数限制 */
        pageInterceptor.setMaxLimit(20L);
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }

    /**
     * 防止全表更新与删除
     */
    @Bean
    public MybatisPlusInterceptor blockAttackInnerInterceptor() {
        /*去除警告，目前暂时无影响，持续关注*/
        disableWarning();
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /*针对 update 和 delete 语句*/
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

    /**
     * 去除Warning
     */
    private static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);
            Class<?> cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ..
        }
    }
}
