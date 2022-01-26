package cn.celess.gums.feign;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.dto.PrmQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    @PostMapping("/permission/list/{serviceId}")
    Response<PageVO<Permission>> queryPermission(@PathVariable("serviceId") Integer serviceId, @RequestBody PrmQueryDTO permission);

}
