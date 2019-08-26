package com.bpm.aop.web;

import com.bpm.aop.service.UserService;
import com.bpm.aop.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bearPotMan on 2019/8/26 15:07.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String phone, String password) {
        return userService.register(phone, password);
    }

    @PostMapping("/login")
    public Result login(String phone, String password) {
        return userService.login(phone, password);
    }
}
