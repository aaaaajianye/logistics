package com.logistics.common;

import lombok.Getter;

/**
 * 返回码统一定义，返回结果统一数据封装
 */
@Getter
public enum ResponseEnum {
    FAIL(0, "失败"),
    SUCCESS(1, "成功"),
    ;

    private int code;
    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}