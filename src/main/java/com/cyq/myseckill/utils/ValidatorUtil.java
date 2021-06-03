package com.cyq.myseckill.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码校验
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/17-23:23
 * @description com.cyq.myseckill.utils
 */
public class ValidatorUtil {
    //此正则表达式只是示例，不是真正的手机号码的正则表达式
    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile) {
        if(StringUtils.isBlank(mobile)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }
}
