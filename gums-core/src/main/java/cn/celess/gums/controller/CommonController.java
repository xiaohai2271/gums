package cn.celess.gums.controller;

import cn.celess.gums.constants.UserConstant;
import cn.celess.gums.dto.SmsDto;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.service.CommonService;
import cn.celess.gums.util.ImageVerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 2021/11/12
 * 公共接口
 *
 * @author 禾几海
 */

@RestController
@RequestMapping("/api/common")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "公共接口")
public class CommonController {

    private final CommonService commonService;

    /**
     * 返回验证码
     */
    @GetMapping(value = "/imgCode", produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation("返回验证码")
    public void getVerifyImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object[] obj = ImageVerifyCodeUtil.createImage();
        request.getSession().setAttribute(UserConstant.IMAGE_CODE_KEY, obj[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) obj[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        // todo:: base64
        os.close();
    }

    /**
     * 生成一个token
     *
     * @return token
     * @see cn.celess.gums.config.ApplicationConfig#tokenExpirationTime
     */
    @GetMapping(value = "/token")
    @ApiOperation("生成一个token")
    public Response<String> requestToken(Integer type) {
        return Response.success(commonService.generateToken(type));
    }


    /**
     * 发送短信验证码
     *
     * @param smsDto 短信参数
     * @return void
     */
    @PostMapping(value = "/sms")
    @ApiOperation("发送短信验证码")
    public Response<Void> requestSms(@RequestBody SmsDto smsDto) {
        commonService.sendSmsCode(smsDto);
        return Response.success(null);
    }
}
