package cn.celess.gums.feign;

import cn.celess.gums.common.constant.CommonConstant;
import cn.celess.gums.common.utils.UserContextUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>date: 2022/01/28</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Component
@Slf4j
public class GumsFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (StringUtils.isNotBlank(UserContextUtil.getToken())) {
            requestTemplate.header(CommonConstant.AUTH_HEADER_KEY, CommonConstant.AUTH_HEADER_VALUE_PREFIX + " " + UserContextUtil.getToken());
        }
    }
}
