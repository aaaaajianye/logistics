package com.logistics.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="公共对象", description="公共字段")
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 自动填充，自动设置新增的时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    /**
     * 自动填充，自动设置修改的时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

}