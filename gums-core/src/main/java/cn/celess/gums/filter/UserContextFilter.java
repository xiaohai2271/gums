package cn.celess.gums.filter;

import cn.celess.gums.constants.ApplicationConstant;
import cn.celess.gums.constants.UserConstant;
import cn.celess.gums.model.UserDetail;
import cn.celess.gums.util.JwtUtil;
import cn.celess.gums.util.RedisUtil;
import cn.celess.gums.util.UserContextUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 2021/12/04
 *
 * @author 禾几海
 */
@Slf4j
@Order(1)
@Component
@WebFilter(filterName = "userContextFilter", urlPatterns = "/*")
public class UserContextFilter implements Filter {
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String auth = ((HttpServletRequest) request).getHeader(ApplicationConstant.AUTH_HEADER_KEY);
            if (auth != null) {
                // FIXME: 2021年12月4日 用户信息存在redis中，需要改进，不需要每次都进行查询
                String token = auth.replaceFirst(ApplicationConstant.AUTH_HEADER_VALUE_PREFIX, "");
                String userJson = RedisUtil.get(UserConstant.getCacheNameOfUser(JwtUtil.getUserIdFromToken(token)));
                try {
                    if (StringUtils.isNoneBlank(userJson)) {
                        UserContextUtil.setUser(objectMapper.readValue(userJson, UserDetail.class));
                    } else {
                        UserContextUtil.clear();
                    }

                } catch (JsonProcessingException e) {
                    log.info("解析缓存的用户数据失败: [{}]", userJson);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            log.warn("用户上下文过滤器异常: [{}]", e.getMessage());
        } finally {
            chain.doFilter(request, response);
        }
    }
}
