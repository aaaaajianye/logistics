package com.logistics.service;

import com.logistics.entity.PageListVo;

/**
 * @author shiwen
 * @date 2020/6/10
 */
public interface IGoodsService {

    /**
     * 新增货物的方法 - 将所有订单打包成货物
     *
     * @param ordersKey
     * @param totalWeight
     * @param totalPrice
     * @param startPoint
     * @param endPoint
     * @return
     */
    String insert(String ordersKey, String totalWeight, String totalPrice, String startPoint, String endPoint);

    /**
     * 根据currentPage进行分页查询（如果有key的话就根据key查询我们的货物）
     *
     * @param currentPage
     * @param ordersKey
     * @return
     */
    PageListVo selectByKey(Integer currentPage, String ordersKey);

}