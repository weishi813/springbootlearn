package com.wsw.shirolearn.common;

import lombok.Getter;

/**
 * 自定义统一响应体
 *
 * @param <T>
 */
@Getter
public class Message<T> {

    private int code;
    private String msg;
    private T data;

    public Message(T data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = ResultCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Message(ResultCodeEnum resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

}


