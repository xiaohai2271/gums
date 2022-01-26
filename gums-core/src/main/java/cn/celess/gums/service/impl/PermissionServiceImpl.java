package cn.celess.gums.service.impl;

import cn.celess.gums.common.entity.Mservice;
import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.mapper.MserviceMapper;
import cn.celess.gums.mapper.PermissionMapper;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private MserviceMapper mserviceMapper;

    @Override
    public PageVO<Permission> queryPage(PrmQueryDTO permission, Integer serviceId) {
        // TODO: 校验key
        String key = permission.getKey();

        Mservice mservice = Optional.of(mserviceMapper.selectById(serviceId))
                .orElseThrow(() -> new RuntimeException("服务不存在"));

        return PageVO.of(
                baseMapper.selectPage(permission.getPageable().build(),
                        new LambdaQueryWrapper<>(Permission.class)
                                .eq(Permission::getServiceId, mservice.getId()))
        );
    }
}
