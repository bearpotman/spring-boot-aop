package com.bpm.aop.service.impl;

import com.bpm.aop.enums.ResultEnum;
import com.bpm.aop.model.User;
import com.bpm.aop.repository.UserRepository;
import com.bpm.aop.service.UserService;
import com.bpm.aop.util.ResultUtil;
import com.bpm.aop.vo.Result;
import com.bpm.aop.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * Created by bearPotMan on 2019/8/26 14:14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        user.setPassword(getMd5(phone, password));
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
        String md5 = getMd5(phone, password);
        if (!md5.equals(user.getPassword())) {
            return ResultUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setPhone(user.getPhone());
        return ResultUtil.success(userVO);
    }

    // md5加密
    private String getMd5(String phone, String password) {
        String salt = "^#)$^&$$!~@+(,.';-`";
        byte[] bytes = new StringBuilder(phone).append(password).append(salt).toString().getBytes();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(bytes);
        return md5DigestAsHex;
    }

}
