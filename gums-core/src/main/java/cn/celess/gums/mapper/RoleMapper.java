package cn.celess.gums.mapper;

import cn.celess.gums.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 服务配置信息表 Mapper 接口
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleByUid(String uid);
}
