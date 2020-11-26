package com.yjy.core.vo;

/**
 * 分页查询参数
 */
public class BaseQueryParam {
    private Integer start;
    private Integer pageSize;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
