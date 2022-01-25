package cn.celess.gums.filter;

import cn.celess.gums.common.model.UserDetail;
import cn.celess.gums.common.utils.UserContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Slf4j
@Order(1)
@Component
@WebFilter(filterName = "userContextFilter", urlPatterns = "/*")
public class PermissionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserDetail user = UserContextUtil.getUser();
        // todo: 1.获取到待调用的method 和对应的权限

        // todo: 2.判断权限

        // todo: 3.如果没有权限，则抛出异常;
        chain.doFilter(request, response);
    }
}
