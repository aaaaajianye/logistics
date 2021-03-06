package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 工种类型实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EmployeeType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工种类型")
    private String typeName;

}