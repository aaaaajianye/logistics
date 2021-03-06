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
 * 员工实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Employee extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工账号")
    private String username;

    @ApiModelProperty(value = "员工密码")
    private String password;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "工种类型")
    private String typeName;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    // 逻辑删除字段
    @TableLogic
    private Integer deleted;

}