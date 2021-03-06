package com.logistics.service;

import com.logistics.entity.Orders;
import com.logistics.entity.PageListVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 实地配送员收取我们的快递的接口
 *
 * @author
 * @date 2020/6/12
 */
public interface IOrdersEmployeeService {

    /**
     * 实地配送员收取快递点的快递订单
     *
     * @param ordersId
     * @return
     */
    String collectOrders(String ordersId, HttpServletRequest request);

    /**
     * 根据工厂id获取订单id
     *
     * @param currentPage
     * @return
     */
    PageListVo<Orders> getOrdersByRepoId(Integer currentPage, HttpServletRequest request);

    /**
     * 设置工厂id到session
     *
     * @param repositoryId
     */
    void setRepositoryId(Integer repositoryId, HttpServletRequest request);

}