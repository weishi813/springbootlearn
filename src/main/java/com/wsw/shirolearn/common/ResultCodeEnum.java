package com.wsw.shirolearn.common;

import lombok.Getter;

/**
 * 响应码枚举
 */
@Getter
public enum ResultCodeEnum {

    FAILED(400, "失败"),
    ERROR(500, "未知错误"),
    SUCCESS(200, "成功");

    private int code;
    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
