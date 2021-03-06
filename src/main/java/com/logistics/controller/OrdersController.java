package com.logistics.controller;

import com.logistics.service.IOrdersService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@Api(description = "订单管理接口")
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService orderService;

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
    @ApiOperation(value = "新增订单", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "startPoint", value = "货物发送点", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "endPoint", value = "货物收货点", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "consignor", value = "发件人姓名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "consignorPhone", value = "发件人电话", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "addressee", value = "收件人姓名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "addresseePhone", value = "收件人电话", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "weight", value = "货物总重", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "notes", value = "寄件备注信息", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "idCardOne", value = "身份证正面照", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "idCardTwo", value = "身份证反面照片", required = true, dataType = "string")
    })
    @GetMapping("/insertOrders")
    public ResultJson insertOrders(String startPoint, String endPoint, String consignor,
                                   String consignorPhone, String addressee, String addresseePhone,
                                   String weight, String notes, String idCardOne, String idCardTwo,
                                   HttpServletRequest request) {
        // 输出测试
        System.out.println(startPoint + endPoint+ consignor+ consignorPhone + addressee +
                addresseePhone+ weight+ notes+ idCardOne+ idCardTwo);
        // 插入数据
        return ResultJson.ok(orderService.insert(startPoint, endPoint, consignor, consignorPhone,addressee,
                            addresseePhone, weight, notes, idCardOne, idCardTwo, request));
    }

    /**
     * 按条件分页查询
     *
     * @param currentPage
     * @param orderId
     * @return
     */
    @ApiOperation(value = "按条件分页查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "orderId", value = "订单号", dataType = "string")
    })
    @GetMapping("/selectByPage")
    public ResultJson selectByPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage, String orderId){
        // 返回一个分页对象
        return ResultJson.ok(orderService.selectByOrderId(currentPage, orderId));
    }

    /**
     * 修改订单信息
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
    @ApiOperation(value = "修改订单信息", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "主键id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "startPoint", value = "货物发送点", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "endPoint", value = "货物收货点", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "consignor", value = "发件人姓名", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "consignorPhone", value = "发件人电话", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "addressee", value = "收件人姓名", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "addresseePhone", value = "收件人电话", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "weight", value = "货物总重", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "notes", value = "寄件备注信息", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "idCardOne", value = "身份证正面照", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "idCardTwo", value = "身份证反面照片", dataType = "string")
    })
    @PutMapping("/updateOrders")
    public ResultJson updateOrders(Integer id, String startPoint, String endPoint, String consignor,
                                  String consignorPhone, String addressee, String addresseePhone,
                                  String weight, String notes, String idCardOne, String idCardTwo) {
        // 更新数据库
        return ResultJson.ok(orderService.update(id, startPoint, endPoint, consignor, consignorPhone,addressee,
                addresseePhone, weight, notes, idCardOne, idCardTwo));
    }

    /**
     * 根据主键id获取订单信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据主键id获取订单信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "主键id", required = true, dataType = "int"),
    })
    @GetMapping("/selectById")
    public ResultJson selectById(Integer id){
        return ResultJson.ok(orderService.selectById(id));
    }

    /**
     * 根据当前session域中的订单号查询订单信息
     *
     * @return
     */
    @ApiOperation(value = "获取session域中的id", httpMethod = "GET")
    @GetMapping("/selectBySessionId")
    public ResultJson selectBySessionId(HttpServletRequest request){
        return ResultJson.ok(orderService.selectBySessionId(request));
    }

    /**
     * 根据主键id进行删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据主键id进行删除", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "主键id", required = true, dataType = "int"),
    })
    @GetMapping("/deleteById")
    public ResultJson deleteById(Integer id){
        return ResultJson.ok(orderService.deleteById(id));
    }

    /**
     * 根据订单号更新订单的付款状态
     *
     * @param orderId
     * @return
     */
    @ApiOperation(value = "根据订单号更新订单的付款状态 付款", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "orderId", value = "订单号", required = true, dataType = "string"),
    })
    @GetMapping("/updateOrderPayStatus")
    public ResultJson updateOrderPayStatus(String orderId) {
        orderService.updateOrderPayStatus(orderId, 1);
        return ResultJson.ok("OK");
    }

    /**
     * 查询未收件状态的快递
     * 订单状态：[0]未收件 [1]已收件 [2]配送中 [3]已收货
     *
     * @return
     */
    @ApiOperation(value = "根据订单的未收件情况进行分页查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页码数", required = true, dataType = "int")
    })
    @GetMapping("/selectPageShoujian")
    public ResultJson selectPageShoujian(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage) {
        return ResultJson.ok(orderService.selectPageShoujian(currentPage));
    }

    /**
     * 查询派件状态的快递
     * 订单状态：[0]未收件 [1]已收件 [2]配送中 [3]已收货
     *
     * @return
     */
    @ApiOperation(value = "根据订单的派送情况进行分页查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页码数", required = true, dataType = "int")
    })
    @GetMapping("/selectPagePaijian")
    public ResultJson selectPagePaijian(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage) {
        return ResultJson.ok(orderService.selectPagePaijian(currentPage));
    }

    @ApiOperation(value = "设置当前订单的id", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "orderSessionId", value = "当前订单的id", required = true, dataType = "int")
    })
    @GetMapping("/setOrderSessionId")
    public ResultJson setOrderSessionId(Integer orderSessionId, HttpServletRequest request) {
        orderService.setOrderSessionId(orderSessionId, request);
        return ResultJson.ok();
    }

    @ApiOperation(value = "设置当前订单号", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "orderSessionOrderId", value = "当前订单的id", required = true, dataType = "int")
    })
    @GetMapping("/setOrderSessionOrderId")
    public ResultJson setOrderSessionOrderId(String orderSessionOrderId, HttpServletRequest request) {
        orderService.setOrderSessionOrderId(orderSessionOrderId, request);
        return ResultJson.ok();
    }

    @ApiOperation(value = "设置当前订单", httpMethod = "GET")
    @GetMapping("/selectByOrderSessionId")
    public ResultJson selectByOrderSessionId(HttpServletRequest request) {
        return ResultJson.ok(orderService.selectByOrderSessionId(request));
    }

    @ApiOperation(value = "快递员收件操作", httpMethod = "GET")
    @GetMapping("/shoujianById")
    public ResultJson shoujianById(String ordersId, HttpServletRequest request) {
        return ResultJson.ok(orderService.shoujianById(ordersId, request));
    }

    @ApiOperation(value = "快递员派件操作", httpMethod = "GET")
    @GetMapping("/paijianById")
    public ResultJson paijianById(String ordersId, HttpServletRequest request) {
        return ResultJson.ok(orderService.paijianById(ordersId, request));
    }

    @ApiOperation(value = "获取订单总价和总数的操作", httpMethod = "GET")
    @GetMapping("/getTotalMoneyAndCounts")
    public ResultJson getTotalMoneyAndCounts(){
        return ResultJson.ok(orderService.getTotalMoneyAndCounts());
    }

}