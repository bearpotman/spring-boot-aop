package com.bpm.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by bearPotMan on 2019/8/26 16:23.
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.bpm.aop.web.*.*(..))")
    public void log() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // url
        log.info("url={}", request.getRequestURL());
        // method
        log.info("method={}", request.getMethod());
        // ip
        log.info("ip={}", request.getRemoteAddr());
        // 类方法
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 参数
        log.info("args={}", Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 返回通知
     *
     * @param resp
     */
    @AfterReturning(returning = "resp", pointcut = "log()")
    public void doAfter(Object resp) {
        log.info("response={}", resp);
    }
}
