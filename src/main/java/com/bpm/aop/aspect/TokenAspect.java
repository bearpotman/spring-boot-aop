package com.bpm.aop.aspect;

import com.bpm.aop.constant.RedisConstant;
import com.bpm.aop.constant.TokenConstant;
import com.bpm.aop.enums.ResultEnum;
import com.bpm.aop.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bearPotMan on 2019/8/26 16:23.
 */
@Component
@Aspect
@Slf4j
public class TokenAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(com.bpm.aop.annotation.AccessToken)")
    public void accessToken() {
    }

    /**
     * 环绕通知
     *
     * ProceedingJoinPoint is only supported for around advice
     * @param joinPoint
     */
    @Around("accessToken()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String headerToken = request.getHeader(TokenConstant.TOKEN);
        if (headerToken == null) {
            return ResultUtil.error(ResultEnum.TOKEN_MISSING_ERROR);
        }
        String key = String.format(RedisConstant.TOKEN_TEMPLATE, headerToken);
        String redisToken = redisTemplate.opsForValue().get(key);
        if (redisToken == null) {
            return ResultUtil.error(ResultEnum.TOKEN_ERROR);
        }
        return joinPoint.proceed();
    }
}
