package com.bpm.aop.enums;

import lombok.Getter;

/**
 * Created by bearPotMan on 2019/8/26 14:51.
 */
@Getter
public enum ResultEnum {
    REGISTER_ERROR(1, "账号已注册，请直接登录"),
    ACCOUNT_ERROR(2, "账号错误"),
    PASSWORD_ERROR(3, "密码错误"),
    TOKEN_MISSING_ERROR(4, "未携带 token"),
    TOKEN_ERROR(5, "token 不符"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
