package com.logistics.entity;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@ApiModel(value = "分页对象", description = "分页对象")
public class PageListVo<T> implements Serializable {

    @ApiModelProperty(value = "页码")
    private int pageNo = 1;

    @ApiModelProperty(value = "页面大小")
    private int pageSize = 20;

    @ApiModelProperty(value = "总页数")
    private long totalPage;

    @ApiModelProperty(value = "总条数")
    private long totalCount;

    @ApiModelProperty(value = "数据列表")
    private List<T> list = new ArrayList<>();

    public PageListVo() {
    }

    public PageListVo(int pageNo, int pageSize, long totalCount, List<T> dataList) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalPage = getTotalPage(totalCount, pageSize);
        this.totalCount = totalCount;
        this.list = dataList;
    }

    /**
     * 获取总页数
     * @param totalCount
     * @param pageSize
     */
    private long getTotalPage(long totalCount, int pageSize) {
        long totalPage;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize +1;
        }
        return totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 返回无数据的分页对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static PageListVo empty(int pageNo, int pageSize) {
        return new PageListVo(pageNo, pageSize, 0, Collections.emptyList());
    }
}