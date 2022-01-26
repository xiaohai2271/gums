package cn.celess.gums.feign;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.dto.PrmQueryDTO;

/**
 * <p>date: 2022/01/26</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
public class FeignFallback implements GumsApiService{
    @Override
    public Response<PageVO<Permission>> queryPermission(Integer serviceId, PrmQueryDTO permission) {
        return null;
    }
}
