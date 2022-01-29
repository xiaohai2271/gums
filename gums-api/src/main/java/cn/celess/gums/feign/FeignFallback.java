package cn.celess.gums.feign;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.model.UserDetail;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.dto.PrmQueryDTO;
import cn.celess.gums.dto.PrmSaveDTO;
import cn.celess.gums.vo.CommonUserVO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>date: 2022/01/26</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Slf4j
public class FeignFallback implements GumsApiService {

    private <T> Response<T> fail() {
        log.debug("调用失败");
        return Response.fail();
    }

    @Override
    public Response<PageVO<Permission>> queryPermission(Integer serviceId, PrmQueryDTO permission) {
        return fail();
    }

    @Override
    public Response<List<Permission>> batchSavePermission(PrmSaveDTO permissions) {
        return fail();
    }

    @Override
    public Response<CommonUserVO> userInfo() {
        return fail();
    }

    @Override
    public Response<String> getCache(String key) {
        return fail();
    }

    @Override
    public Response<UserDetail> detailUserInfo() {
        return fail();
    }
}
