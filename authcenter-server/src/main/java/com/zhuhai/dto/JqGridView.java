package com.zhuhai.dto;


import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/12
 * Time: 17:55
 */
public class JqGridView<T> implements Serializable {

    /**总页数**/
    private long totalPage;
    /**当前页**/
    private long currentPage;
    /**总记录数**/
    private long records;
    /**数据**/
    private List<T> rows;

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
