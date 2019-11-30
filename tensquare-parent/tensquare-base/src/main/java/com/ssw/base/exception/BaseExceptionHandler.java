package com.ssw.base.exception;

import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultModel exceptionHandler(Exception e){
        e.printStackTrace();
        return  new ResultModel(false, StatusCode.ERROR,e.getMessage());
    }
}
