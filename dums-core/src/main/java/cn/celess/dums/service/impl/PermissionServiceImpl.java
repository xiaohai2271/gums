package cn.celess.dums.service.impl;

import cn.celess.dums.entity.Permission;
import cn.celess.dums.mapper.PermissionMapper;
import cn.celess.dums.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
