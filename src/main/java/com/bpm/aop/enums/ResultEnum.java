package com.bpm.aop.enums;

import lombok.Getter;

/**
 * Created by bearPotMan on 2019/8/26 14:51.
 */
@Getter
public enum ResultEnum {
    REGISTER_ERROR(1, "该账号已注册，请直接登录"),
    ACCOUNT_ERROR(2, "账号错误"),
    PASSWORD_ERROR(3, "密码错误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
