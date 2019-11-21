package com.ssw.entity;

import java.util.List;

/**
 * @author ssw
 * @description 分页查询返回结果
 * @date 2019/11/21
 * @time 14:22
 */
public class PageResult<T> {
    private Long total;

    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }


}
