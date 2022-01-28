package cn.celess.gums.controller;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.dto.PrmSaveDTO;
import cn.celess.gums.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("/list/{serviceId}")
    public Response<PageVO<Permission>> queryPermission(@PathVariable("serviceId") Integer serviceId,
                                                        @RequestBody PrmQueryDTO permission) {
        return Response.success(permissionService.queryPage(permission, serviceId));
    }

    @PutMapping("/save")
    public Response<List<Permission>> batchSavePermission(@RequestBody PrmSaveDTO permissions) {
        return Response.success(permissionService.savePermission(permissions));
    }
}
