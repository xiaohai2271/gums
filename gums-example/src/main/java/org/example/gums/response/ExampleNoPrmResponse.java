package org.example.gums.response;

import cn.celess.gums.RequirePrmResponse;
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
    @Override
    public String response(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return "您无权访问该页面";
    }
}
