package com.ssw.entity;

/**
 * @description 通用返回结果模板
 * @author ssw
 * @date 2019/11/21
 * @time 14:20
 */
public class ResultModel {

    private Boolean flag;

    private Integer code;

    private String message;

    private Object data;

    public ResultModel() {
    }

    public ResultModel(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public ResultModel(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
