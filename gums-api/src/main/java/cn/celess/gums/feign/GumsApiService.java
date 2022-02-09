package cn.celess.gums.feign;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.model.UserDetail;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.dto.PrmSaveDTO;
import cn.celess.gums.vo.CommonUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>date: 2022/01/26</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@FeignClient(
        name = "gums",
        url = "http://localhost:8765/",
        fallback = FeignFallback.class
)
public interface GumsApiService {
    @PostMapping("/api/permission/list/{serviceId}")
    Response<PageVO<Permission>> queryPermission(@PathVariable("serviceId") Integer serviceId, @RequestBody PrmQueryDTO permission);

    @GetMapping("/api/user/")
    public Response<CommonUserVO> userInfo();

    @PutMapping("/api/permission/batch")
    Response<List<Permission>> batchSaveOrUpdatePermission(@RequestBody PrmSaveDTO permissions);


    @GetMapping("/api/cache")
    Response<String> getCache(String key);

    @GetMapping("/api/user/detail")
    Response<UserDetail> detailUserInfo();

}
