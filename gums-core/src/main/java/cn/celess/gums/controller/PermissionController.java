package cn.celess.gums.controller;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("/list/{serviceId}")
    public Response<PageVO<Permission>> queryPermission(@PathVariable("serviceId") Integer serviceId,
                                                        @RequestBody PrmQueryDTO permission) {
        return Response.success(permissionService.queryPage(permission, serviceId));
    }
}
