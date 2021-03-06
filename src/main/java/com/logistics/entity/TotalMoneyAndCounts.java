package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 获取总订单数和总营业额
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TotalMoneyAndCounts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总订单量")
    private Integer totalCount;

    @ApiModelProperty(value = "总营业额")
    private Double totalMoney;

}