package com.cyq.myseckill.exception;

import com.cyq.myseckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局异常
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/18-10:14
 * @description com.cyq.myseckill.exception
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{
    private RespBeanEnum respBeanEnum;
}
