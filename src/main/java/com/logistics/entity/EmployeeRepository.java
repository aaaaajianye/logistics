package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工和公司的对应关系实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EmployeeRepository extends BaseEntity {

    @ApiModelProperty(value = "公司id")
    private Integer repositoryId;

    @ApiModelProperty(value = "员工id")
    private Integer employeeId;

}