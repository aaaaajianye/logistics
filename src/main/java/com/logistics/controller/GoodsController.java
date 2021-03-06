package com.logistics.controller;

import com.logistics.service.IGoodsService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(description = "货物管理接口")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 新增货物
     *
     * @param ordersKey
     * @param totalWeight
     * @param totalPrice
     * @param startPoint
     * @param endPoint
     * @return
     */
    @ApiOperation(value = "新增货物", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ordersKey", value = "用于存储orders的key的值", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "totalWeight", value = "该批货物的总重量/kg", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "totalPrice", value = "该批货物的总价", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "startPoint", value = "货物发送点", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "endPoint", value = "货物收获点", required = true, dataType = "string")
    })
    @PostMapping("/insertGoods")
    public ResultJson insertGoods(String ordersKey, String totalWeight, String totalPrice, String startPoint, String endPoint){
        return ResultJson.ok(goodsService.insert(ordersKey, totalWeight, totalPrice, startPoint, endPoint));
    }

    /**
     * 指定分页查询分页内容
     *
     * @param currentPage
     * @param ordersKey
     * @return
     */
    @ApiOperation(value = "分页查询货物", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "指定当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "ordersKey", value = "用于存储orders的key的值", dataType = "string")
    })
    @GetMapping("/selectByKey")
    public ResultJson selectByKey(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage, String ordersKey){
        return ResultJson.ok(goodsService.selectByKey(currentPage, ordersKey));

    }

}