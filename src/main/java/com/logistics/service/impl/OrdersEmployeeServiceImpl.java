package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logistics.entity.Orders;
import com.logistics.entity.OrdersEmployee;
import com.logistics.entity.PageListVo;
import com.logistics.mapper.OrdersEmployeeMapper;
import com.logistics.service.IOrdersEmployeeService;
import com.logistics.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author shiwen
 * @date 2020/6/12
 */
@Service
public class OrdersEmployeeServiceImpl implements IOrdersEmployeeService {

    @Autowired
    private IOrdersService ordersService;

    @Resource
    private OrdersEmployeeMapper ordersEmployeeMapper;

    /**
     * 快递员收集订单，还是一个一个来比较好
     * 逻辑：如果实地配送员接收了订单，就产生关系，此时修改我们订单的状态为1已收单
     *
     * @param ordersId
     * @return
     */
    @Override
    public String collectOrders(String ordersId, HttpServletRequest request) {
        // 初始化一个session
        HttpSession session = request.getSession();
        if (session.getAttribute("employeeId") != null && !"".equals(session.getAttribute("employeeId"))) {
            // 获取存在session域中的员工id
            int employeeId = (int) session.getAttribute("employeeId");
            // 如果员工id不为空并且不是空字符串的话，就直接跟订单产生关系
            if (employeeId != 0) {
                // 首先将订单和员工产生关系
                OrdersEmployee ordersEmployee = new OrdersEmployee(ordersId, employeeId, 0, 0);
                Integer count = ordersEmployeeMapper.insert(ordersEmployee);
                // 修改订单的状态为已收货
                ordersService.updateOrderStatus(ordersId, 1);
                if (count > 0) {
                    return "订单收取成功";
                } else {
                    return "订单收取失败";
                }
            }
        }
        return "实地配送员暂未登录";
    }

    /**
     * 根据工厂id获取订单id
     *
     * @param currentPage
     * @return
     */
    @Override
    public PageListVo<Orders> getOrdersByRepoId(Integer currentPage, HttpServletRequest request) {
        HttpSession session = request.getSession();
        int repositoryId = (int) session.getAttribute("repository_id");
        QueryWrapper wrapper = new QueryWrapper();
        List<Orders> ordersList = new ArrayList<>();
        // 使用条件语句进行查询
        wrapper.eq("repository_id", repositoryId);
        // 根据工厂id查询出所有的订单
        List<OrdersEmployee> list = ordersEmployeeMapper.selectList(wrapper);
        // 使用迭代器选出所有的订单对象
        Iterator it = list.iterator();
        // 使用订单对象取出每一个订单对象存入集合
        while (it.hasNext()) {
            // 获取出每一个对象
            OrdersEmployee ordersEmployee = (OrdersEmployee) it.next();
            System.out.println("订单号：" + ordersEmployee.getOrdersId());
            //ordersService.deleteConditionOrders(ordersEmployee.getOrdersId());
            // 根据订单id获取订单对象
            Orders orders = ordersService.selectConditionOrders(ordersEmployee.getOrdersId());
            if (orders != null) {
                System.out.println(orders);
                // 把订单对象放入集合
                ordersList.add(orders);
            }
        }
        // 返回一个封装的数据分页对象
        return new PageListVo(currentPage, 10, ordersList.size(), ordersList);
    }

    /**
     * 设置工厂id到session
     *
     * @param repositoryId
     */
    @Override
    public void setRepositoryId(Integer repositoryId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("repository_id", repositoryId);
    }

}