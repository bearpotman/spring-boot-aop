package com.bpm.aop.service;

import com.bpm.aop.vo.Result;

/**
 * Created by bearPotMan on 2019/8/26 14:13.
 */
public interface UserService {

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @return
     */
    Result register(String phone, String password);

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    Result login(String phone, String password);
}
