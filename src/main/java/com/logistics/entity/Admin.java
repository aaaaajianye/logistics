package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 管理员实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Admin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员账号")
    private String username;

    @ApiModelProperty(value = "管理员密码")
    private String password;

    @ApiModelProperty(value = "管理员姓名")
    private String name;

    @ApiModelProperty(value = "管理员电话")
    private String phone;

}