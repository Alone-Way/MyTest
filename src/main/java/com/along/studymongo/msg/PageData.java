package com.along.studymongo.msg;

import java.util.List;

public class PageData<T> {
    long total;
    List<T> rows;

    public PageData(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageData() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}