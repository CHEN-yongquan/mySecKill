package com.cyq.myseckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyq.myseckill.pojo.Order;
import com.cyq.myseckill.pojo.SeckillOrder;
import com.cyq.myseckill.pojo.User;
import com.cyq.myseckill.service.IGoodsService;
import com.cyq.myseckill.service.IOrderService;
import com.cyq.myseckill.service.ISeckillOrderService;
import com.cyq.myseckill.vo.GoodsVo;
import com.cyq.myseckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/19-8:55
 * @description com.cyq.myseckill.controller
 */
@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;

    /**
     * 秒杀
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user, Long goodsId) {
        if(null == user) {
            return "login";
        }
        log.info("doSeckill: " + user.toString());
        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goodsVo.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "seckillFail";
        }
        //判断是否重复抢购，MyBatisPlus的写法
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
                .eq("user_id", user.getId())
                .eq("goods_id", goodsId));
        if(null != seckillOrder) {
            model.addAttribute("errmsg", RespBeanEnum.REPEAT_ERROR.getMessage());
            return "seckillFail";
        }
        //
        Order order = orderService.seckill(user, goodsVo);
        model.addAttribute("order", order);
        model.addAttribute("goods", goodsVo);
        return "orderDetail";
    }
}
