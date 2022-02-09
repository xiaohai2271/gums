package cn.celess.gums;

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
@Component("requirePrmResponse")
public interface RequirePrmResponse {
    void response(HttpServletRequest request, HttpServletResponse response);
}
