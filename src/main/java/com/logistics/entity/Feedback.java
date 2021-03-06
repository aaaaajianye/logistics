package com.logistics.entity;

import com.logistics.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 订单实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Feedback extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "反馈标题")
    private String title;

    @ApiModelProperty(value = "反馈内容")
    private String desc;

    @ApiModelProperty(value = "反馈图片路径")
    private String photosUrl;

    @ApiModelProperty(value = "反馈处理状态")
    private Integer status;

}