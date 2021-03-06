package com.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *  订单实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "发货点")
    private String startPoint;

    @ApiModelProperty(value = "收货点")
    private String endPoint;

    @ApiModelProperty(value = "总距离")
    private Double distance;

    @ApiModelProperty(value = "发货人姓名")
    private String consignor;

    @ApiModelProperty(value = "发货人电话")
    private String consignorPhone;

    @ApiModelProperty(value = "收件人姓名")
    private String addressee;

    @ApiModelProperty(value = "收件人电话")
    private String addresseePhone;

    @ApiModelProperty(value = "寄件规格/kg")
    private String weight;

    @ApiModelProperty(value = "寄件总价")
    private String totalPrice;

    @ApiModelProperty(value = "付款状态：[0]未付款 [1]已付款 [2]已退款")
    private Integer payStatus;

    @ApiModelProperty(value = "寄件备注信息")
    private String notes;

    @ApiModelProperty(value = "订单状态：[0]未收件 [1]已收件 [2]配送中 [3]已收货")
    private Integer orderStatus;

    @ApiModelProperty(value = "身份证正面")
    private String idCardOne;

    @ApiModelProperty(value = "身份证反面")
    private String idCardTwo;

    // 逻辑删除字段
    @TableLogic
    private Integer deleted;

}