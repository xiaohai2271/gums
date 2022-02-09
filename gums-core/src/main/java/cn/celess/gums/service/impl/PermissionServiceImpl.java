package cn.celess.gums.service.impl;

import cn.celess.gums.common.entity.Mservice;
import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.entity.ServiceCfg;
import cn.celess.gums.constants.ServiceConstant;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.dto.PrmSaveDTO;
import cn.celess.gums.exception.CommonException;
import cn.celess.gums.mapper.MserviceMapper;
import cn.celess.gums.mapper.PermissionMapper;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.mapper.ServiceCfgMapper;
import cn.celess.gums.common.response.ResponseConstant;
import cn.celess.gums.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Resource
    private ServiceCfgMapper serviceCfgMapper;


    @Override
    public PageVO<Permission> queryPage(PrmQueryDTO permission, Integer serviceId) {
        Mservice mservice = checkServiceReq(serviceId, permission.getSecretKey());
        return PageVO.of(
                baseMapper.selectPage(permission.getPageable().build(),
                        new LambdaQueryWrapper<>(Permission.class)
                                .eq(Permission::getServiceId, mservice.getId()))
        );
    }

    @Override
    public List<Permission>  saveOrUpdatePermission(PrmSaveDTO permission) {
        checkServiceReq(permission.getServiceId(), permission.getSecretKey());
        List<Permission> permissionList = permission.getPermissions();
        permissionList.forEach(p -> p.setCreateDt(LocalDateTime.now()));
        saveOrUpdateBatch(permissionList);
        return permissionList;
    }

    private Mservice checkServiceReq(Integer serviceId, String secretKey) {
        Mservice mservice = Optional.of(mserviceMapper.selectById(serviceId))
                .orElseThrow(() -> new CommonException(ResponseConstant.SERVICE_NOT_EXIST));
        ServiceCfg serviceCfg = Optional.of(serviceCfgMapper.selectOne(new LambdaQueryWrapper<ServiceCfg>()
                .eq(ServiceCfg::getServiceId, serviceId)
                .eq(ServiceCfg::getCfgKey, ServiceConstant.SERVICE_CFG_SECRET_KEY)
        )).orElseThrow(() -> new CommonException(ResponseConstant.SERVICE_SECRET_KEY_NOT_EXIST));

        if (!secretKey.equals(serviceCfg.getCfgValue())) {
            throw new CommonException(ResponseConstant.SERVICE_SECRET_KEY_NOT_MATCH);
        }
        return mservice;
    }
}
