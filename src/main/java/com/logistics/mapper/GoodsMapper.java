package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 货物dao层接口
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}