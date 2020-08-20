package com.wsw.shirolearn.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {

    private int code;
    private String msg;

    public APIException() {
        this(500, "接口错误");
    }

    public APIException(String msg) {
        this.code = 500;
        this.msg = msg;
    }

    public APIException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
