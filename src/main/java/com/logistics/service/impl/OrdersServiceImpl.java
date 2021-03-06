package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.entity.*;
import com.logistics.mapper.OrdersEmployeeMapper;
import com.logistics.mapper.OrdersMapper;
import com.logistics.service.IEmployeeRepository;
import com.logistics.service.IOrdersService;
import com.logistics.utils.LocationUtils;
import com.logistics.utils.LogisticsPriceUtil;
import com.logistics.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 订单接口
 *
 * @author
 * @date 2020/6/10
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrdersEmployeeMapper ordersEmployeeMapper;

    @Autowired
    private IEmployeeRepository employeeRepository;

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
    @Override
    public String insert(String startPoint, String endPoint, String consignor,
                         String consignorPhone, String addressee, String addresseePhone,
                         String weight, String notes, String idCardOne, String idCardTwo,
                         HttpServletRequest request) {
        // 生成订单号并保存到session域，主要是用于支付用的
        String orderId = TimeUtils.getOrderNumber();
        HttpSession session = request.getSession();
        session.setAttribute("orderId", orderId);
        // 初始化一个order对象进行赋值
        Orders orders = new Orders(orderId, startPoint, endPoint,
                LocationUtils.getDistance(startPoint, endPoint), consignor, consignorPhone,
                addressee, addresseePhone, weight, ""+LogisticsPriceUtil.cost(Double.parseDouble(weight), LocationUtils.getDistance(startPoint, endPoint)),
                0, notes, 0, idCardOne, idCardTwo, 0);
        System.out.println(orders);
        Integer count = ordersMapper.insert(orders);
        // 判断是否插入成功
        if (count > 0) {
            return "订单下单成功";
        } else {
            return "订单下单失败";
        }
    }

    /**
     * 根据指定页码，或者根据订单号进行模糊查询
     *
     * @param currentPage
     * @param orderId
     * @return
     */
    @Override
    public PageListVo selectByOrderId(Integer currentPage, String orderId) {
        // 分页对象
        Page<Orders> page = new Page<>(currentPage, 10);
        QueryWrapper wrapper = new QueryWrapper();
        // 判断我们的订单号是否为空，如果订单号不为空的话就直接查询
        if (orderId != null && !"".equals(orderId)) {
            // 条件模糊查询
            wrapper.like("order_id", orderId);
        }
        // 进行查询的操作
        ordersMapper.selectPage(page, wrapper);
        List<Orders> records = page.getRecords();
        Integer totalCount = ordersMapper.selectCount(wrapper);
        // 返回一个分页对象
        return new PageListVo(currentPage, 10, totalCount, records);
    }

    /**
     * 更新订单信息
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
    @Override
    public String update(Integer id, String startPoint, String endPoint, String consignor, String consignorPhone,
                         String addressee, String addresseePhone, String weight, String notes,
                         String idCardOne, String idCardTwo) {
        // 根据id进行查询
        Orders orders = ordersMapper.selectById(id);
        if (orders != null && orders.getId() > 0) {
            if (startPoint != null) {
                orders.setStartPoint(startPoint);
            }
            if (endPoint != null) {
                orders.setEndPoint(endPoint);
            }
            if (consignor != null) {
                orders.setConsignor(consignor);
            }
            if (consignorPhone != null) {
                orders.setConsignorPhone(consignorPhone);
            }
            if (addressee != null) {
                orders.setAddressee(addressee);
            }
            if (addresseePhone != null) {
                orders.setAddresseePhone(addresseePhone);
            }
            if (weight != null) {
                orders.setWeight(weight);
            }
            if (notes != null) {
                orders.setNotes(notes);
            }
            if (idCardOne != null) {
                orders.setIdCardOne(idCardOne);
            }
            if (idCardTwo != null) {
                orders.setIdCardTwo(idCardTwo);
            }
            // 如果对象的起始点和终点都不为空，并且重量不为空的话，就直接计算我们的距离价格
            if (orders.getStartPoint() != null && !"".equals(orders.getStartPoint()) && orders.getEndPoint() != null &&
                    !"".equals(orders.getEndPoint()) && orders.getWeight() != null && !"".equals(orders.getWeight())) {
                // 设置距离进行更新
                orders.setDistance(LocationUtils.getDistance(orders.getStartPoint(), orders.getEndPoint()));
                // 设置价格进行更新
                orders.setTotalPrice(""+LogisticsPriceUtil.cost(Double.parseDouble(orders.getWeight()), LocationUtils.getDistance(orders.getStartPoint(), orders.getEndPoint())));
            }
            // 根据id查询出来的对象，当然里面也有id。然后再根据对象id进行更新
            Integer count = ordersMapper.updateById(orders);
            if (count > 0) {
                return "订单更新成功";
            } else {
                return "订单更新失败";
            }
        } else {
            return "该订单不存在";
        }
    }

    /**
     * 根据id进行删除操作
     *
     * @param id
     * @return
     */
    @Override
    public String deleteById(Integer id) {
        Integer count = ordersMapper.deleteById(id);
        if (count > 0) {
            return "订单删除成功";
        } else {
            return "订单删除失败";
        }
    }

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    @Override
    public Orders selectById(Integer id) {
        Orders orders = ordersMapper.selectById(id);
        if (orders != null && orders.getId() > 0) {
            // 返回订单对象
            return orders;
        } else {
            return null;
        }
    }

    /**
     * 获取存储在session域中的orderId进行查询
     */
    @Override
    public Orders selectBySessionId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获取存在session域中的orderId
        String orderId = (String) session.getAttribute("orderId");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_id", orderId);
        Orders orders = ordersMapper.selectOne(wrapper);
        return orders;
    }

    /**
     * 根据订单号修改订单的配送状态
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_id", orderId);
        // 根据订单号获取单个订单对象
        Orders order = ordersMapper.selectOne(wrapper);
        if (order != null) {
            // 更新订单的当前配送状态
            order.setOrderStatus(orderStatus);
            // 更新order对象
            ordersMapper.updateById(order);
        }
    }

    /**
     * 更新订单的付款状态
     *
     * @param orderId
     * @param payStatus
     */
    @Override
    public void updateOrderPayStatus(String orderId, Integer payStatus) {
        QueryWrapper wrapper = new QueryWrapper();
        // 根据order_id进行查询
        wrapper.eq("order_id", orderId);
        // 根据订单号获取单个订单对象
        Orders order = ordersMapper.selectOne(wrapper);
        if (order != null) {
            // 更新订单的当前配送状态
            order.setPayStatus(payStatus);
            // 更新order对象
            ordersMapper.updateById(order);
        }
    }

    /**
     * 根据指定页码，或者根据订单号进行模糊查询
     *
     * @param currentPage
     * @return
     */
    @Override
    public PageListVo selectPageShoujian(Integer currentPage) {
        // 分页对象
        Page<Orders> page = new Page<>(currentPage, 10);
        QueryWrapper wrapper = new QueryWrapper();
        // 判断我们的订单号是否为空，如果订单号不为空的话就直接查询
        // 条件查询
        wrapper.eq("order_status", 0);
        // 进行查询的操作
        ordersMapper.selectPage(page, wrapper);
        List<Orders> records = page.getRecords();
        Integer totalCount = ordersMapper.selectCount(wrapper);
        // 返回一个分页对象
        return new PageListVo(currentPage, 10, totalCount, records);
    }

    /**
     * 根据指定页码，或者根据订单号进行模糊查询
     *
     * @param currentPage
     * @return
     */
    @Override
    public PageListVo selectPagePaijian(Integer currentPage) {
        // 分页对象
        Page<Orders> page = new Page<>(currentPage, 10);
        QueryWrapper wrapper = new QueryWrapper();
        // 判断我们的订单号是否为空，如果订单号不为空的话就直接查询
        // 条件查询
        wrapper.eq("order_status", 2);
        // 进行查询的操作
        ordersMapper.selectPage(page, wrapper);
        List<Orders> records = page.getRecords();
        Integer totalCount = ordersMapper.selectCount(wrapper);
        // 返回一个分页对象
        return new PageListVo(currentPage, 10, totalCount, records);
    }

    /**
     * 订单号确定并且status是1（已收件）
     *
     * @param orderId
     * @return
     */
    @Override
    public Orders selectConditionOrders(String orderId) {
        QueryWrapper wrapper = new QueryWrapper();
        // 订单号相同进行筛选并且要order_status为1的
        wrapper.eq("order_id", orderId);
        wrapper.eq("order_status", 1);
        return ordersMapper.selectOne(wrapper);
    }

    public void deleteConditionOrders(String orderId) {
        QueryWrapper wrapper = new QueryWrapper();
        // 订单号相同进行筛选并且要order_status为1的
        wrapper.eq("order_id", orderId);
        // 如果订单号不是1了就直接删除
        wrapper.notLike("order_status", 1);
        ordersEmployeeMapper.delete(wrapper);
    }

    /**
     * 设置当前订单的SESSIONID
     */
    public void setOrderSessionId(Integer orderSessionId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("orderSessionId", orderSessionId);
    }

    /**
     * 设置当前订单的SESSIONID
     */
    public void setOrderSessionOrderId(String orderSessionOrderId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("orderId", orderSessionOrderId);
    }

    /**
     * 获取存储在session域中的orderId进行查询
     */
    @Override
    public Orders selectByOrderSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获取存在session域中的orderId
        int orderSessionId = (int) session.getAttribute("orderSessionId");
        return selectById(orderSessionId);
    }

    @Override
    public String shoujianById(String ordersId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获取存在session域中的employeeId
        int employeeId = (int) session.getAttribute("employeeId");
        EmployeeRepository employeeRepository = this.employeeRepository.getEmployeeRepository(request);
        // 更新订单的配送状态
        updateOrderStatus(ordersId,1);
        OrdersEmployee ordersEmployee = new OrdersEmployee();
        ordersEmployee.setOrdersId(ordersId);
        // 一收件就是送达公司了
        ordersEmployee.setStatus(1);
        // 设置公司id
        ordersEmployee.setRepositoryId(employeeRepository.getRepositoryId());
        ordersEmployee.setEmployeeId(employeeId);
        int count = ordersEmployeeMapper.insert(ordersEmployee);
        if (count > 0) {
            return "SUCCESS";
        } else {
            return "ERROR";
        }
    }

    @Override
    public String paijianById(String ordersId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获取存在session域中的employeeId
        int employeeId = (int) session.getAttribute("employeeId");
        EmployeeRepository employeeRepository = this.employeeRepository.getEmployeeRepository(request);
        // 更新订单的配送状态
        updateOrderStatus(ordersId,3);
        OrdersEmployee ordersEmployee = new OrdersEmployee();
        ordersEmployee.setOrdersId(ordersId);
        // 设置公司id
        ordersEmployee.setRepositoryId(employeeRepository.getRepositoryId());
        ordersEmployee.setEmployeeId(employeeId);
        int count = ordersEmployeeMapper.insert(ordersEmployee);
        if (count > 0) {
            return "SUCCESS";
        } else {
            return "ERROR";
        }
    }

    @Override
    public TotalMoneyAndCounts getTotalMoneyAndCounts() {
        Double totalMoney = 0.0;
        List<Orders> ordersList = ordersMapper.selectList(null);
        Iterator it = ordersList.iterator();
        while (it.hasNext()) {
            Orders orders = (Orders) it.next();
            if (orders.getTotalPrice() != null && !"".equals(orders.getTotalPrice())) {
                // 只有价格不为空的时候才能进行价格的累加
                totalMoney = Double.parseDouble(orders.getTotalPrice()) + totalMoney;
            }
        }
        // 获取总数
        Integer totalCount = ordersMapper.selectCount(null);
        TotalMoneyAndCounts totalMoneyAndCounts = new TotalMoneyAndCounts();
        totalMoneyAndCounts.setTotalCount(totalCount);
        totalMoneyAndCounts.setTotalMoney(totalMoney);
        return totalMoneyAndCounts;
    }

}