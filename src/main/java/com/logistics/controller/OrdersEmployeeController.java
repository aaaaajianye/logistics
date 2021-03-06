package com.logistics.controller;

import com.logistics.service.IOrdersEmployeeService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



@Api(description = "实地配送员收取快递订单的接口")
@RestController
@RequestMapping("/ordersEmployeeController")
public class OrdersEmployeeController {

    @Autowired
    private IOrdersEmployeeService iOrdersEmployeeService;

    /**
     * 实地配送员收取快递订单的接口
     *
     * @param ordersId
     * @param request
     * @return
     */
    @ApiOperation(value = "实地配送员收取快递订单的接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ordersId", value = "订单号", required = true, dataType = "string")
    })
    @PostMapping("/collectOrders")
    public ResultJson collectOrders(String ordersId, HttpServletRequest request) {
        return ResultJson.ok(iOrdersEmployeeService.collectOrders(ordersId, request));
    }

    @ApiOperation(value = "设置工厂id到session", httpMethod = "GET")
    @GetMapping("/setRepositoryId")
    public ResultJson setRepositoryId(Integer repositoryId, HttpServletRequest request) {
        iOrdersEmployeeService.setRepositoryId(repositoryId, request);
        return ResultJson.ok();
    }

    @ApiOperation(value = "根据工厂id获取分页数据对象", httpMethod = "GET")
    @GetMapping("/getRepositoryPage")
    public ResultJson getRepositoryPage(Integer currentPage, HttpServletRequest request) {
        return ResultJson.ok(iOrdersEmployeeService.getOrdersByRepoId(currentPage, request));
    }

}