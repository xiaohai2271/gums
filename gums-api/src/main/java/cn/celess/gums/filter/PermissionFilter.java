package cn.celess.gums.filter;

import cn.celess.gums.RequirePrmResponse;
import cn.celess.gums.common.annotations.PermissionRequest;
import cn.celess.gums.common.model.UserDetail;
import cn.celess.gums.common.utils.UserContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Slf4j
@Order(2)
@Component
@WebFilter(filterName = "permissionFilter", urlPatterns = "/*")
@RequiredArgsConstructor
public class PermissionFilter implements Filter {
    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final RequirePrmResponse requirePrmResponse;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Map<RequestMappingInfo, HandlerMethod> handler = requestMappingHandlerMapping.getHandlerMethods();
            for (RequestMappingInfo info : handler.keySet()) {
                boolean anyMatch = info.getDirectPaths().stream().anyMatch(a -> a.equals(((HttpServletRequest) request).getServletPath()));
                if (anyMatch) {
                    HandlerMethod method = handler.get(info);
                    PermissionRequest permissionRequest = method.getMethodAnnotation(PermissionRequest.class);
                    if (permissionRequest != null && !UserContextUtil.hasPermission(permissionRequest.value())) {
                        log.info("没有权限");
                        if (requirePrmResponse != null) {
                            String res = requirePrmResponse.response((HttpServletRequest) request, (HttpServletResponse) response);
                            response.getWriter().println(res);
                        }
                        return;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }
}
