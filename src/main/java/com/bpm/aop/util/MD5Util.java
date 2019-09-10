package com.bpm.aop.util;

import org.springframework.util.DigestUtils;

/**
 * Created by bearPotMan on 2019/9/10 15:27.
 */
public class MD5Util {

    private static final String salt = "^#)$^&$$!~@+(,.';-`";

    private MD5Util() {
    }

    /**
     * md5加密
     *
     * @param args
     * @return
     */
    public static String getMd5(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
        }
        sb.append(salt);
        byte[] bytes = sb.toString().getBytes();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(bytes);
        return md5DigestAsHex;
    }
}
