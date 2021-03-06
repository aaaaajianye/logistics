package com.logistics.utils;

import com.logistics.common.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回json扩展类，对请求响应统一格式
 */
@ApiModel(value="Json对象", description="json数据格式")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultJson<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "提示消息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 成功，默认方法
     * @return
     */
    public static ResultJson ok(){
        return ResultJson.builder().code(ResponseEnum.SUCCESS.getCode()).msg(ResponseEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 成功，带返回的数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultJson<T> ok(T data) {
        return ResultJson.<T>builder().code(ResponseEnum.SUCCESS.getCode()).msg(ResponseEnum.SUCCESS.getMessage()).data(data).build();
    }

    /**
     * 成功，自定义状态码，消息内容，数据
     * @return
     */
    public static <T> ResultJson<T> ok(ResponseEnum responseEnum, T data){
        return ResultJson.<T>builder().code(responseEnum.getCode()).msg(responseEnum.getMessage()).data(data).build();
    }

    /**
     * 失败，默认方法
     * @return
     */
    public static ResultJson failed(){
        return ResultJson.builder().code(ResponseEnum.FAIL.getCode()).msg(ResponseEnum.FAIL.getMessage()).build();
    }

}