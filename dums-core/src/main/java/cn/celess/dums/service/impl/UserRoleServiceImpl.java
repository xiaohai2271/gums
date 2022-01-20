package cn.celess.dums.service.impl;

import cn.celess.dums.entity.UserRole;
import cn.celess.dums.mapper.UserRoleMapper;
import cn.celess.dums.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022-01-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
