package com.kw.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一错误处理
 */
@ControllerAdvice(basePackages = {"com.kw.controller"})
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception e){
        Map<String, Object> attrs = new HashMap<>();
        String errorcode = e.getMessage();
        ErrorEnum errorEnum = ErrorEnum.getByCode(errorcode);
        attrs.put("message",errorEnum.getMessage());
        attrs.put("code",errorEnum.getCode());
        attrs.put("canRetry",errorEnum.getCanRetry());
        attrs.put("type","advice"); //区别于 MyErrorController 处理方式
        return new ResponseEntity(attrs, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * MyErrorController和errorControllerAdvice同时处理异常，会先进errorControllerAdvice进行处理，
     * 除非errorControllerAdvice处理的时候抛出异常才会进MyErrorController再做处理。
     * 总结：springBoot提供的统一错误处理的功能，首先是继承BasicErrorController这种形式实现的。
     * 第二种方式是ControllerAdvice，其实这个并不是为了统一错误处理提供的功能，
     * 它是Controller的增强，不仅仅可以用来做错误处理，还可以做其他功能
     */
}
