package com.bpm.aop.util;

import com.bpm.aop.enums.ResultEnum;
import com.bpm.aop.vo.Result;

/**
 * Created by bearPotMan on 2019/8/26 14:48.
 */
public class ResultUtil {

    private ResultUtil() {

    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Object object){
        Result result = new Result();
        result.setData(object);
        result.setCode(0);
        result.setMessage("成功");
        return result;
    }

    public static Result error(ResultEnum loginEnum) {
        Result result = new Result();
        result.setCode(loginEnum.getCode());
        result.setMessage(loginEnum.getMessage());
        return result;
    }
}
