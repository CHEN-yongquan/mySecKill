package com.cyq.myseckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyq.myseckill.exception.GlobalException;
import com.cyq.myseckill.mapper.UserMapper;
import com.cyq.myseckill.pojo.User;
import com.cyq.myseckill.service.IUserService;
import com.cyq.myseckill.utils.CookieUtil;
import com.cyq.myseckill.utils.MD5Util;
import com.cyq.myseckill.utils.UUIDUtil;
import com.cyq.myseckill.vo.LoginVo;
import com.cyq.myseckill.vo.RespBean;
import com.cyq.myseckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyongquan
 * @since 2021-05-16
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RedisTemplate<String, Object> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 登录
     * @param loginVo
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        /*
        已经自定义了注解IsMobile来完成这部分参数校验的工作
        //参数校验
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if(!ValidatorUtil.isMobile(mobile)) {
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }
         */
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if(null == user) {
            //return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if(!MD5Util.fromPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            //return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成cookie并放入session
        String ticket = UUIDUtil.uuid();
        //request.getSession().setAttribute(ticket, user);

        //将用户信息存入Redis中
        redisTemplate.opsForValue().set("user:" + ticket, user);

        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        log.info("userTicket:" + userTicket);
        if(StringUtils.isBlank(userTicket)) {
            return null;
        }
        User user = (User)redisTemplate.opsForValue().get("user:" + userTicket);
        //若不为空，重新设置cookie值，以防万一的考虑
        if(user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }
}
