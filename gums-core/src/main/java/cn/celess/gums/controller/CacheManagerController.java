package cn.celess.gums.controller;

import cn.celess.gums.common.response.Response;
import cn.celess.gums.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>date: 2022/01/28</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@RestController
@RequestMapping("/api/cache")
public class CacheManagerController {
    @GetMapping("")
    public Response<String> getCache(String key) {
        return Response.success(RedisUtil.get(key));
    }
}
