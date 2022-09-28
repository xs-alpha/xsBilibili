package com.xiaosheng.video.support.result;

import java.io.Serializable;

public class PageRequest implements Serializable {
    private Long pageNo;
    private Long pageSize;

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public PageRequest(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public PageRequest() {
    }
}
