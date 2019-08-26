package com.bpm.aop.vo;

import lombok.Data;

/**
 * Created by bearPotMan on 2019/8/26 14:46.
 */
@Data
public class Result<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;


}
