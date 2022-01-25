package cn.celess.gums.listener;

import cn.celess.gums.common.annotations.PermissionRequest;
import cn.celess.gums.constants.GumsApiConstants;
import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.properties.GumsProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
public class GumsApplicationListener implements ApplicationListener<ApplicationStartedEvent> {
    private GumsProperties gumsProperties;
    private CacheManager cacheManager;

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        ConfigurableApplicationContext ctx = event.getApplicationContext();

        gumsProperties = ctx.getBean("gumsProperties", GumsProperties.class);
        cacheManager = ctx.getBean(CacheManager.class);

        List<Permission> permissions = new ArrayList<>(); // todo: 查数据库， 本service的权限

        // fixme: test data
        {
            permissions.add(new Permission()
                    .setPermissionCode("test1")
                    .setPermissionName("测试权限1")
                    .setId(1)
                    .setRemark("测试权限1")
                    .setServiceId(1)
                    .setCreateDt(LocalDateTime.now())
            );
        }

        Cache cache = cacheManager.getCache(GumsApiConstants.CACHE_NAME);
        Objects.requireNonNull(cache).put(GumsApiConstants.PERMISSION_KEY_NAME, permissions);

        ctx.getBeansWithAnnotation(Controller.class).forEach((k, v) -> {
            List<Method> methodList = Arrays.stream(v.getClass().getMethods()).filter(m -> m.isAnnotationPresent(PermissionRequest.class)).collect(Collectors.toList());
            methodList.forEach(m -> {
                PermissionRequest permissionRequest = m.getAnnotation(PermissionRequest.class);
                Permission permission = new Permission();
                permission.setServiceId(gumsProperties.getServiceId())
                        .setPermissionCode(permissionRequest.value());
                boolean isExist = permissions.stream().anyMatch(p -> p.getPermissionCode().equals(permission.getPermissionCode()));
                // 查询权限是否存在
                if (!isExist) {
                    // 接口查询权限不存在，调接口新增权限
                    permission.setPermissionName(permissionRequest.name())
                            .setRemark(permissionRequest.description())
                            .setCreateDt(LocalDateTime.now());
                    // todo: 存数据库

                    // 新增权限成功，更新权限缓存
                    permissions.add(permission);
                    Objects.requireNonNull(cache).put(GumsApiConstants.PERMISSION_KEY_NAME, permissions);
                }
            });
        });
    }
}
