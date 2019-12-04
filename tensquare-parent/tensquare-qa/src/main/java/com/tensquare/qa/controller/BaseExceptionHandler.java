package com.tensquare.qa.controller;
import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel error(Exception e){
        e.printStackTrace();        
        return new ResultModel(false, StatusCode.ERROR, "执行出错");
    }
}
