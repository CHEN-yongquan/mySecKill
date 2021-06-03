package com.cyq.myseckill.vo;

import com.cyq.myseckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 登录参数
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/17-10:02
 * @description com.cyq.myseckill.vo
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private  String password;

}
