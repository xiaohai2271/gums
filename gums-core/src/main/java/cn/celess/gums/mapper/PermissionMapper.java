package cn.celess.gums.mapper;

import cn.celess.gums.common.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionByRoleId(Integer roleId);
}
