package cn.celess.gums.filter;

import cn.celess.gums.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String auth = ((HttpServletRequest) request).getHeader(CommonConstant.AUTH_HEADER_KEY);
        if (auth != null) {
            String token = auth.replaceFirst(CommonConstant.AUTH_HEADER_VALUE_PREFIX, "");
            // todo： 拿着token 去查用户信息   绑定到UserContext上
        }

    }
}
