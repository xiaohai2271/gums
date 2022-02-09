package org.example.gums.response;

import cn.celess.gums.RequirePrmResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>date: 2022/01/29</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Component
public class ExampleNoPrmResponse implements RequirePrmResponse {
    @SneakyThrows
    @Override
    public void response(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(200);
        response.getWriter().println("{\"code\":\"-1\",\"message\": \"无权限\"}");
    }
}
