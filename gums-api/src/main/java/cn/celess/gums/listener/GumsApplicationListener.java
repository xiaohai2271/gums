package cn.celess.gums.listener;

import cn.celess.gums.common.annotations.PermissionRequest;
import cn.celess.gums.common.context.GumsApplicationContext;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.page.Pageable;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.constants.GumsApiConstants;
import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.dto.PrmSaveDTO;
import cn.celess.gums.feign.GumsApiService;
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
import java.util.*;
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

        GumsApiService apiService = ctx.getBean(GumsApiService.class);

        Response<PageVO<Permission>> pageVOResponse = apiService.queryPermission(gumsProperties.getServiceId(),
                new PrmQueryDTO().setPageable(new Pageable(1L, Long.MAX_VALUE))
                        .setSecretKey(gumsProperties.getSecretKey())
        );

        PageVO<Permission> pageVO = Optional.of(pageVOResponse.getData())
                .orElseThrow(() -> new RuntimeException("获取权限列表失败"));

        List<Permission> permissions = pageVO.getList();

        Cache cache = cacheManager.getCache(GumsApiConstants.CACHE_NAME);
        Objects.requireNonNull(cache).put(GumsApiConstants.PERMISSION_KEY_NAME, permissions);

        List<Permission> batchSaveList = new ArrayList<>();
        Map<String, Object> objectMap = ctx.getBeansWithAnnotation(Controller.class);
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {

            List<Method> methodList = Arrays.stream(entry.getValue().getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(PermissionRequest.class))
                    .collect(Collectors.toList());
            for (Method m : methodList) {
                PermissionRequest permissionRequest = m.getAnnotation(PermissionRequest.class);
                Permission permission = new Permission();
                permission.setServiceId(gumsProperties.getServiceId())
                        .setPermissionCode(permissionRequest.value());
                boolean isExist = permissions.stream().anyMatch(p -> p.getPermissionCode().equals(permission.getPermissionCode()));
                // 查询权限是否存在
                if (!isExist) {
                    // 接口查询权限不存在，调接口新增权限
                    permission.setPermissionName(permissionRequest.name())
                            .setRemark(permissionRequest.description());
                    batchSaveList.add(permission);
                    // 新增权限成功，更新权限缓存
                }
            }
        }
        PrmSaveDTO prmSaveDTO = new PrmSaveDTO().setPermissions(batchSaveList)
                .setServiceId(gumsProperties.getServiceId())
                .setSecretKey(gumsProperties.getSecretKey());
        Response<List<Permission>> listResponse = apiService.batchSavePermission(prmSaveDTO);

        List<Permission> response = Optional.of(listResponse.getData()).orElseThrow(() -> new RuntimeException("新增权限失败"));
        permissions.addAll(response);
    }
}
