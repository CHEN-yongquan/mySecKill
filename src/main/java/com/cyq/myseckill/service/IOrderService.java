package com.cyq.myseckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyq.myseckill.pojo.Order;
import com.cyq.myseckill.pojo.User;
import com.cyq.myseckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyongquan
 * @since 2021-05-18
 */
public interface IOrderService extends IService<Order> {

    /**
     * 秒杀
     * @param user
     * @param goodsVo
     * @return
     */
    Order seckill(User user, GoodsVo goodsVo);
}
