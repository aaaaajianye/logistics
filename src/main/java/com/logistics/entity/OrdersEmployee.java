package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工收取快递订单的实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrdersEmployee extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    private String ordersId;

    @ApiModelProperty(value = "收件员工的id")
    private int employeeId;

    @ApiModelProperty(value = "订单是否已送达公司:[0]未送达 [1]已送达")
    private Integer status;

    @ApiModelProperty(value = "公司id")
    private Integer repositoryId;

}