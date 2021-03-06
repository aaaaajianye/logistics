package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 货车实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TruckRepository extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "货车车牌号")
    private String carNo;

    @ApiModelProperty(value = "公司id")
    private Integer repositoryId;

    @ApiModelProperty(value = "货车是否正在配送")
    private Integer isTransport;

}