package com.logistics.service;

import com.logistics.entity.Orders;
import com.logistics.entity.PageListVo;
import com.logistics.entity.TotalMoneyAndCounts;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单接口
 *
 * @author
 * @date 2020/6/10
 */
public interface IOrdersService {

    /**
     * 新增订单
     *
     * @param startPoint
     * @param endPoint
     * @param consignor
     * @param consignorPhone
     * @param addressee
     * @param addresseePhone
     * @param weight
     * @param notes
     * @param idCardOne
     * @param idCardTwo
     * @return
     */
    String insert(String startPoint, String endPoint, String consignor,
                  String consignorPhone, String addressee, String addresseePhone,
                  String weight, String notes, String idCardOne, String idCardTwo,
                  HttpServletRequest request);

    /**
     * 根据订单号和当前页码进行分页查询
     *
     * @param orderId
     * @return
     */
    PageListVo selectByOrderId(Integer currentPage, String orderId);

    /**
     * 根据主键id进行更新操作
     *
     * @param id
     * @param startPoint
     * @param endPoint
     * @param consignor
     * @param consignorPhone
     * @param addressee
     * @param addresseePhone
     * @param weight
     * @param notes
     * @param idCardOne
     * @param idCardTwo
     * @return
     */
    String update(Integer id, String startPoint, String endPoint, String consignor,
                  String consignorPhone, String addressee, String addresseePhone,
                  String weight, String notes, String idCardOne, String idCardTwo);

    /**
     * 根据主键id进行删除操作
     *
     * @param id
     * @return
     */
    String deleteById(Integer id);

    /**
     * 根据主键id查询对象
     *
     * @param id
     * @return
     */
    Orders selectById(Integer id);

    /**
     * 获取session域中的id，并进行查询
     */
    Orders selectBySessionId(HttpServletRequest request);

    /**
     * 根据订单号更新订单的配送状态
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 将未付款的订单改为已付款
     *
     * @param orderId
     * @param payStatus
     */
    void updateOrderPayStatus(String orderId, Integer payStatus);

    /**
     * 根据订单的收件状态进行分页查询
     *
     * @param currentPage
     * @return
     */
    PageListVo selectPageShoujian(Integer currentPage);

    /**
     * 根据订单的派件状态进行分页查询
     *
     * @param currentPage
     * @return
     */
    PageListVo selectPagePaijian(Integer currentPage);

    /**
     * 根据订单的派件状态查询所有的订单 status = 1 and 订单号确定
     *
     * @return
     */
    Orders selectConditionOrders(String orderId);

    /**
     * 设置当前的订单id
     *
     * @param orderSessionId
     * @param request
     */
    void setOrderSessionId(Integer orderSessionId, HttpServletRequest request);

    /**
     * 设置订单号到session中去
     *
     * @param orderSessionId
     * @param request
     */
    void setOrderSessionOrderId(String orderSessionId, HttpServletRequest request);

    /**
     * 根据主键查询订单
     *
     * @param request
     * @return
     */
    Orders selectByOrderSessionId(HttpServletRequest request);

    void deleteConditionOrders(String orderId);

    /**
     * 收件
     *
     * @param id
     * @param request
     * @return
     */
    String shoujianById(String id, HttpServletRequest request);

    /**
     * 派件操作
     *
     * @param id
     * @param request
     * @return
     */
    String paijianById(String id, HttpServletRequest request);

    /**
     * 获取总订单额和总订单数
     */
    TotalMoneyAndCounts getTotalMoneyAndCounts();

}