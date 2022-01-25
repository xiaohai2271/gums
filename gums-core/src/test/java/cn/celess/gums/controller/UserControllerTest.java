package cn.celess.gums.controller;

import cn.celess.gums.BaseTest;
import cn.celess.gums.config.ApplicationConfig;
import cn.celess.gums.constants.UserConstant;
import cn.celess.gums.dto.UserLoginDto;
import cn.celess.gums.common.enums.LoginType;
import cn.celess.gums.exception.CommonException;
import cn.celess.gums.response.Response;
import cn.celess.gums.response.ResponseConstant;
import cn.celess.gums.util.AesEncryptUtil;
import cn.celess.gums.util.RedisUtil;
import cn.celess.gums.util.WebUtil;
import cn.celess.gums.vo.LoginUserVO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>date: 2022/01/21</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
class UserControllerTest extends BaseTest {

    @Resource
    UserController userController;

    Function<Response<LoginUserVO>, LoginUserVO> checkSuccess = vo -> {
        assertEquals(ResponseConstant.SUCCESS.getStatus(), vo.getStatus());
        assertNotNull(vo.getData());
        assertNotNull(vo.getData().getId());
        assertNotNull(vo.getData().getToken());
        return vo.getData();
    };

    @Test
    void customLogin() {
        RedisUtil.delete(UserConstant.getCacheNameOfLoginFailedRecord("zhenghai", null));
        RedisUtil.delete(UserConstant.getCacheNameOfLoginFailedRecord("zhenghai", "13300000000"));
        RedisUtil.delete(UserConstant.getCacheNameOfLoginFailedRecord("zhenghai11", null));

        UserLoginDto customLogin = new UserLoginDto();
        customLogin.setLoginType(LoginType.CUSTOM_LOGIN)
                .setUsername("zhenghai")
                .setPassword(AesEncryptUtil.encrypt("123456789", customLogin.getUsername()));
        Response<LoginUserVO> login = userController.login(customLogin);
        LoginUserVO userVO = checkSuccess.apply(login);
        assertEquals(customLogin.getUsername(), userVO.getUsername());

        // 测试登录失败
        customLogin.setLoginType(LoginType.CUSTOM_LOGIN)
                .setUsername("zhenghai")
                .setPassword(AesEncryptUtil.encrypt("12345678", customLogin.getUsername()));
        int count = 1;
        Boolean verifyCode = false;
        for (; count <= ApplicationConfig.getInstance().maxLoginFailedTimes + 3; count++) {
            try {
                Object imgStatus = WebUtil.getHttpSession().getAttribute(UserConstant.getCacheNameOfDisplayableImgCode("zhenghai"));
                if (verifyCode && Boolean.TRUE.equals(imgStatus)) {
                    WebUtil.getHttpSession().setAttribute(UserConstant.IMAGE_CODE_KEY,"123456");
                    customLogin.setImgCode("123456");
                }
                login = userController.login(customLogin);
            } catch (CommonException e) {
                Integer status = e.getResponse().getStatus();
                if (count > ApplicationConfig.getInstance().maxLoginFailedTimes + 2) {
                    assertEquals(ResponseConstant.ACCOUNT_TEMP_LOCKED.getStatus(), status);
                    continue;
                }
                if (count == ApplicationConfig.getInstance().showImgCodedThreshold + 1) {
                    assertEquals(ResponseConstant.VERIFY_IMAGE_CODE_FIRST.getStatus(), status);
                    verifyCode = true;
                    continue;
                }
                assertEquals(ResponseConstant.LOGIN_FAILED.getStatus(), status);
            }
        }

        // 测试账户不存在
        customLogin.setLoginType(LoginType.CUSTOM_LOGIN)
                .setUsername("zhenghai11")
                .setPassword(AesEncryptUtil.encrypt("12345678", customLogin.getUsername()));
        try {
            login = userController.login(customLogin);
        } catch (CommonException e) {
            assertEquals(ResponseConstant.LOGIN_FAILED.getStatus(), e.getResponse().getStatus());
        }
    }

    @Test
    void registration() {
    }

    @Test
    void logout() {
    }

    @Test
    void userInfo() {
    }

    @Test
    void resetPwd() {
    }

    @Test
    void pageQueryUser() {
    }
}