package com.bpm.aop.service.impl;

import com.bpm.aop.constant.RedisConstant;
import com.bpm.aop.constant.TokenConstant;
import com.bpm.aop.enums.ResultEnum;
import com.bpm.aop.model.User;
import com.bpm.aop.repository.UserRepository;
import com.bpm.aop.service.UserService;
import com.bpm.aop.util.MD5Util;
import com.bpm.aop.util.ResultUtil;
import com.bpm.aop.vo.Result;
import com.bpm.aop.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by bearPotMan on 2019/8/26 14:14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private HttpServletResponse response;

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public Result register(String phone, String password) {
        User byPhone = userRepository.findByPhone(phone);
        if (byPhone != null) {
            return ResultUtil.error(ResultEnum.REGISTER_ERROR);
        }
        User user = new User();
        user.setPhone(phone);
        user.setPassword(MD5Util.getMd5(phone, password));
        user.setCreateTime(new Date());
        userRepository.save(user);
        return ResultUtil.success();
    }

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public Result login(String phone, String password) {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            return ResultUtil.error(ResultEnum.ACCOUNT_ERROR);
        }
        String md5 = MD5Util.getMd5(phone, password);
        if (!md5.equals(user.getPassword())) {
            return ResultUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        // redis 缓存
        String token = MD5Util.getMd5(MD5Util.getMd5(phone, password));
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                phone,
                TokenConstant.EXPIRE,
                TimeUnit.SECONDS);
        // 响应头设置 TOKEN
        response.setHeader(TokenConstant.TOKEN, token);

        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setPhone(user.getPhone());
        return ResultUtil.success(userVO);
    }

    /**
     * 根据手机号获取用户信息（测试）
     *
     * @param phone
     * @return
     */
    @Override
    public Result getUserByPhone(String phone) {
        User user = userRepository.findByPhone(phone);
        return ResultUtil.success(user);
    }

}
