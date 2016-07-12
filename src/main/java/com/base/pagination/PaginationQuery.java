package com.base.pagination;

/**
 * Created by summer on 16/7/9.
 */
public class PaginationQuery {

    private static final int PAGE_INDEX = 1;
    private static final int PAGE_SIZE = 20;

    private int pageIndex;
    private int pageSize;
    private int totalPages;
    private long totalRecords;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
