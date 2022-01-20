package cn.celess.dums.login;

import cn.celess.dums.context.DumsApplicationContext;
import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.exception.ArgumentException;
import cn.celess.dums.exception.LoginFailedException;
import cn.celess.dums.response.ResponseConstant;
import cn.celess.dums.util.DataProcessorUtil;
import cn.celess.dums.vo.LoginUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 2021/12/17
 *
 * @author 禾几海
 */
@Component
@Slf4j
public class LoginProcessorFactory {

    private List<LoginProcessor> loginProcessors = null;

    /**
     * 调用登录处理器的入口
     *
     * @param loginDto 请求数据
     * @return 登录成功 用户信息
     * @throws LoginFailedException 登陆失败，错误信息
     */
    public LoginUserVO login(UserLoginDto loginDto) throws LoginFailedException {
        checkAndInitProcessors();
        DataProcessorUtil.handlerLoginData(loginDto);
        for (LoginProcessor processor : loginProcessors) {
            if (processor.enable() && Objects.equals(processor.getLoginType(), loginDto.getType())) {
                return processor.doLogin(loginDto);
            }
        }
        throw new ArgumentException(ResponseConstant.ARGUMENT_OF_TYPE_ERROR);
    }


    /**
     * 检查并初始化处理器
     */
    private void checkAndInitProcessors() {
        if (this.loginProcessors == null) {
            this.loginProcessors = new ArrayList<>();
        }
        if (this.loginProcessors.size() == 0) {
            String[] beanNamesForType = DumsApplicationContext.getApplicationContext().getBeanNamesForType(LoginProcessor.class);
            for (String beanName : beanNamesForType) {
                LoginProcessor loginProcessor = DumsApplicationContext.getApplicationContext().getBean(beanName, LoginProcessor.class);
                this.loginProcessors.add(loginProcessor);
            }
        }
    }

}
