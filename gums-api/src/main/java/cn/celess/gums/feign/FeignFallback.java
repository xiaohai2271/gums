package cn.celess.gums.feign;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.model.UserDetail;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.dto.PrmSaveDTO;
import cn.celess.gums.vo.CommonUserVO;

import java.util.List;

/**
 * <p>date: 2022/01/26</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
public class FeignFallback implements GumsApiService {
    @Override
    public Response<PageVO<Permission>> queryPermission(Integer serviceId, PrmQueryDTO permission) {
        return Response.fail();
    }

    @Override
    public Response<List<Permission>> batchSavePermission(PrmSaveDTO permissions) {
        return Response.fail();
    }

    @Override
    public Response<CommonUserVO> userInfo() {
        return Response.fail();
    }

    @Override
    public Response<String> getCache(String key) {
        return Response.fail();
    }

    @Override
    public Response<UserDetail> detailUserInfo() {
        return Response.fail();
    }
}
