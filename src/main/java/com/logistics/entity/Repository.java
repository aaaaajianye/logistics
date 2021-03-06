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
 * 分公司仓库实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Repository extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分公司仓库名")
    private String repositoryName;

    @ApiModelProperty(value = "分公司地址")
    private String address;

}