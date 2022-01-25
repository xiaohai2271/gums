package cn.celess.gums.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2021/12/04
 *
 * @author 禾几海
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface PermissionRequest {
    /**
     * permission code 权限码
     */
    String value() default "";

    /**
     * 权限名称
     */
    String name() default "";

    /**
     * 权限描述
     */
    String description() default "";
}
