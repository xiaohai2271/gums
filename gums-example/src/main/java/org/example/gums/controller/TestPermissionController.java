package org.example.gums.controller;

import cn.celess.gums.common.annotations.PermissionRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>date: 2022/01/25</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@RestController
public class TestPermissionController {


    @GetMapping("/noPermission")
    public String noPermission() {
        return "Now you see me!";
    }


    @GetMapping({"/permission/test1", "/test1"})
    @PermissionRequest(value = "test1", name = "测试权限1", description = "测试权限1的描述")
    public String test1Permission() {
        return "Now you see me! I`m test1";
    }

    @GetMapping("/permission/test2")
    @PermissionRequest(value = "test2", name = "测试权限2", description = "测试权限2的描述")
    public String test2Permission() {
        return "Now you see me! I`m test2";
    }

}
