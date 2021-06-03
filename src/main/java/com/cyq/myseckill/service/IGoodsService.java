package com.cyq.myseckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyq.myseckill.pojo.Goods;
import com.cyq.myseckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyongquan
 * @since 2021-05-18
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 根据id获取商品详情
     * @param goodsId
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
