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


    @GetMapping({"/permission/test1","/test1"})
    @PermissionRequest("test1")
    public String test1Permission() {
        return "Now you see me! I`m test1";
    }

    @GetMapping("/permission/test2")
    @PermissionRequest("test2")
    public String test2Permission() {
        return "Now you see me! I`m test2";
    }

}
