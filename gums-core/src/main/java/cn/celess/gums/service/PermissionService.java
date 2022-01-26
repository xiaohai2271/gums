package cn.celess.gums.service;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.page.PageVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 查询权限列表
     *
     * @param permission 权限查询条件
     * @param serviceId  服务id
     * @return 权限列表
     */
    PageVO<Permission> queryPage(PrmQueryDTO permission, Integer serviceId);
}
