package com.cyq.myseckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyq.myseckill.pojo.Goods;
import com.cyq.myseckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyongquan
 * @since 2021-05-18
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 根据id获取商品详情
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
