package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 货物实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Goods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "多个批次的所有订单号集合的key")
    private String ordersKey;

    @ApiModelProperty(value = "货物总重")
    private String totalWeight;

    @ApiModelProperty(value = "货物总订单价")
    private String totalPrice;

    @ApiModelProperty(value = "货物配送起点")
    private String startPoint;

    @ApiModelProperty(value = "货物配送终点")
    private String endPoint;

}