package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单dao层接口
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}