package com.xiaosheng.video.support.result;


import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private List<T> list;
    private Long pageNo = 1L;
    private Long total = 0L;
    private Long pageSize = 10L;

    public PageResult() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public PageResult(List<T> list, Long pageNo, Long total, Long pageSize) {
        this.list = list;
        this.pageNo = pageNo;
        this.total = total;
        this.pageSize = pageSize;
    }
}
