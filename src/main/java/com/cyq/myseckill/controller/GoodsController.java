package com.cyq.myseckill.controller;

import com.cyq.myseckill.pojo.User;
import com.cyq.myseckill.service.IGoodsService;
import com.cyq.myseckill.service.IUserService;
import com.cyq.myseckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 商品
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/18-11:22
 * @description com.cyq.myseckill.controller
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;

    /**
     * MVC优化之后的商品跳转页，将判断用户是否登录的部分与接口主要逻辑解耦
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
//        if(user == null) {
//            log.info("user为空");
//            return "login";
//        }
//        log.info("toList: " + user.toString());
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

    /**
     * 跳转商品详情页
     * @param goodsId
     * @return
     */
    @RequestMapping("/toDetail{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        if(user == null) {
            return "login";
        }
        log.info("toDetail: " + user.toString());

        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态，0-秒杀倒计时，1-秒杀进行中，2-秒杀已结束
        int seckillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;
        //秒杀未开始
        if(nowDate.before(startDate)) {
            remainSeconds = (int)((startDate.getTime() - nowDate.getTime()) / 1000);

        }else if(nowDate.after(endDate)) {
            //秒杀已结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";
    }





    /*
    MVC优化之前的商品跳转页，需要在接口内判断用户是否登录
    @RequestMapping("/toList")
    //public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
    public String toList(HttpServletRequest request, HttpServletResponse response,
                         Model model, @CookieValue("userTicket") String ticket) {
        //若cookie值为空，则跳转登录页
        if(StringUtils.isBlank(ticket)) {
            return "login";
        }
        //获取cookie对应的session中的用户对象，若为空，跳转登录页
        //User user = (User)session.getAttribute(ticket);

        User user = userService.getUserByCookie(ticket, request, response);

        if(null == user) {
            return "login";
        }
        //都没问题的话，把用户对象传到前端页面去
        model.addAttribute("user", user);
        return "goodsList";
    }
     */
}
