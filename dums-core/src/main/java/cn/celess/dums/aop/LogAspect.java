package cn.celess.dums.aop;

import cn.celess.dums.util.WebUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * <p>date: 2022/01/21</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Resource
    ObjectMapper objectMapper;

    @Pointcut("execution(* cn.celess.*.controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
//        if (UserContextUtil.getUser() != null) {
//            String address = WebUtil.getIpAddress();
//            MDC.put("info", "[" + UserContextUtil.getUser().getAccount() + ":" + address + "]");
//        }

        StringBuilder logTemplate = new StringBuilder();
        logTemplate.append("<===> ");
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();
        logTemplate.append("执行方法[{}],入参：{}, 出参：{}");
        log.debug(
                logTemplate.toString(),
                methodName,
                objectMapper.writeValueAsString(joinPoint.getArgs()),
                objectMapper.writeValueAsString(result)
        );
        log.info("<===> 执行方法[{}]耗时：{}ms", methodName, (endTime - startTime) * 1.0 / 1e6);
        MDC.clear();
        return result;
    }

}
