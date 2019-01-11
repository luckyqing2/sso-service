package com.luckyqing.sso.common.domain;

import java.io.Serializable;

/**
 * @program: notice
 * @description: datatable数据表格返回
 * @author: "清歌"
 * @create: 2018-08-17 15:00
 **/
public class DataTableResult implements Serializable {

            private Integer draw; // 第几次
            private Object data; // 存放数据
            private Integer recordsTotal;// 总条数
            private Integer recordsFiltered;    // 过滤之后的条数
            private Integer length ;//页大小
            private Integer start;//第几行开始

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
