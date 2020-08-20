package com.wsw.shirolearn.exception;

import com.wsw.shirolearn.common.Message;
import com.wsw.shirolearn.common.ResultCodeEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(APIException.class)
    public Message<String> APIExceptionHandler(APIException e) {
        return new Message<>(ResultCodeEnum.FAILED, e.getMsg());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Message<String> AuthenticationExceptionHandler(AuthenticationException e) {
        return new Message<>(ResultCodeEnum.FAILED, e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public Message<String> AuthorizationExceptionHandler() {
        return new Message<>(ResultCodeEnum.FAILED,"权限不足");
    }

}
