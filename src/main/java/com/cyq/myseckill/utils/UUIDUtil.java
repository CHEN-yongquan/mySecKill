package com.cyq.myseckill.utils;

import java.util.UUID;

/**
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/18-10:54
 * @description com.cyq.myseckill.utils
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
