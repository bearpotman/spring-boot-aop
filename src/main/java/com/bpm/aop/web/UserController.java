package com.bpm.aop.web;

import com.bpm.aop.annotation.AccessToken;
import com.bpm.aop.service.UserService;
import com.bpm.aop.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bearPotMan on 2019/8/26 15:07.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(String phone, String password) {
        return userService.register(phone, password);
    }

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(String phone, String password) {
        return userService.login(phone, password);
    }

    @GetMapping("/getUser")
    @AccessToken
    public Result getUser(String phone) {
        return userService.getUserByPhone(phone);
    }

}
