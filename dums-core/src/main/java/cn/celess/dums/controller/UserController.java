package cn.celess.dums.controller;

import cn.celess.dums.annotations.PermissionRequest;
import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.dto.UserPageQueryDto;
import cn.celess.dums.dto.UserRegDto;
import cn.celess.dums.dto.UserResetPwdDto;
import cn.celess.dums.page.PageVO;
import cn.celess.dums.response.Response;
import cn.celess.dums.service.UserService;
import cn.celess.dums.vo.CommonUserVO;
import cn.celess.dums.vo.LoginUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>date: 2022/01/20</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户接口")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Response<LoginUserVO> login(@RequestBody UserLoginDto dto) {
        return Response.success(userService.login(dto));
    }

    @PostMapping("/reg")
    @ApiOperation("用户注册")
    public Response<CommonUserVO> registration(@RequestBody UserRegDto dto) {
        return Response.success(userService.registration(dto));
    }

    @GetMapping("/logout")
    @ApiOperation("用户退出")
    public Response<Void> logout() {
        userService.logout();
        return Response.success(null);
    }

    @GetMapping({"/", ""})
    @ApiOperation("获取当前登录用户信息")
    public Response<CommonUserVO> userInfo() {
        return Response.success(userService.getUserInfo());
    }

    @PostMapping("/reset/pwd")
    @ApiOperation("重置密码")
    public Response<Void> resetPwd(@RequestBody UserResetPwdDto userRegDto) {
        userService.resetPassword(userRegDto);
        return Response.success(null);
    }

    @PostMapping("/user/list")
    @ApiOperation("分页获取用户列表")
    @PermissionRequest()
    public Response<PageVO<CommonUserVO>> pageQueryUser(@RequestBody UserPageQueryDto userRegDto) {
        return Response.success(userService.pageQuery(userRegDto));
    }
}