package com.cyq.myseckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * MD5工具类
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/16-20:31
 * @description com.cyq.myseckill.utils
 */
@Component
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    //这个salt主要是和前端的salt进行统一
    private static final String SALT = "1a2b3c4d";

    //第一次加密，用户端加密再传到后端，是用户端完成的方法
    public static String inputPassToFromPass(String inputPass) {
        //没有用完整的SALT，是为提高安全性的一种处理方式
        String str = "" + SALT.charAt(0) + SALT.charAt(2) + inputPass +
                SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    //第二次加密，后端加密再到数据库
    //这个salt与上一个不同，是不固定的
    public static String fromPassToDBPass(String fromPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + fromPass +
                salt.charAt(5) +salt.charAt(4);
        return md5(str);
    }

    //后端真正调用的方法
    public static String inputPassToDBPass(String inputPass, String salt) {
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, salt);
        return dbPass;
    }


//    public static void main(String[] args) {
//        System.out.println(inputPassToFromPass("123456")); //d3b1294a61a07da9b49b6e22b2cbd7f9
//        System.out.println(fromPassToDBPass(
//                "d3b1294a61a07da9b49b6e22b2cbd7f9", "5e6f7g8h"));
//        System.out.println(inputPassToDBPass("123456", "5e6f7g8h"));
//    }
}
