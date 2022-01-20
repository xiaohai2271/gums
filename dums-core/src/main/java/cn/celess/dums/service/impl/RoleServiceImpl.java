package cn.celess.dums.service.impl;

import cn.celess.dums.entity.Role;
import cn.celess.dums.mapper.RoleMapper;
import cn.celess.dums.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务配置信息表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
