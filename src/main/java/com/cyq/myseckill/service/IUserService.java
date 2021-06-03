package com.cyq.myseckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyq.myseckill.pojo.User;
import com.cyq.myseckill.vo.LoginVo;
import com.cyq.myseckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyongquan
 * @since 2021-05-16
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param loginVo
     * @return
     */
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据cookie获取用户
     * @param userTicket
     * @return
     */
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);
}
