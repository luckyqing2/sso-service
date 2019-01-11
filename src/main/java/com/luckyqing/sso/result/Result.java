package com.luckyqing.sso.result;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


/**
 * 结果
 */
public class Result implements Serializable {

    // 日志
    private static final Logger log = LoggerFactory.getLogger(Result.class);

    // 处理结果
    private boolean success;
    // 代码
    private String code;
    // 消息
    private String msg;
    // 异常信息
    private String ex;
    // 数据
    private Object data;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    public Result() {
    }

    public Result(Object data) {
        this.data = data;
        this.code = Message.M0001;
        this.msg = "处理成功";
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
