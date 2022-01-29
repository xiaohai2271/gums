package cn.celess.gums.filter;

import cn.celess.gums.common.constant.CommonConstant;
import cn.celess.gums.common.entity.User;
import cn.celess.gums.common.model.UserDetail;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.common.utils.UserContextUtil;
import cn.celess.gums.feign.GumsApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>date: 2022/01/28</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Slf4j
@Order(1)
@Component
@WebFilter(filterName = "userContextFilter", urlPatterns = "/*")
public class UserContextFilter implements Filter {
    @Resource
    private GumsApiService gumsApiService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String auth = ((HttpServletRequest) request).getHeader(CommonConstant.AUTH_HEADER_KEY);
        if (auth != null) {
            String token = auth.replaceFirst(CommonConstant.AUTH_HEADER_VALUE_PREFIX, "");
            UserContextUtil.setToken(token);

            Response<UserDetail> userDetailResponse = gumsApiService.detailUserInfo();
            if (userDetailResponse.getData() != null) {
                User user = userDetailResponse.getData().getUser();
                log.debug("成功获取用户信息, [id: {}, username: '{}']", user.getId(), user.getUsername());
                UserContextUtil.setUser(userDetailResponse.getData());
            }
        }
        chain.doFilter(request, response);
    }
}
