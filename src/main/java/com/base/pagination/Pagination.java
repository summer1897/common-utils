package com.base.pagination;


import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Author: hzduhao
 * Date: 2016/7/12
 * Time: 14:55
 * pagination entity mainly used to paginate
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = -2073005876645989075L;
    private final int DEFAULT_PAGE_INDEX = 1;
    private final int DEFAULT_PAGE_SIZE = 10;
    private final int DEFAULT_TOTAL_RECORDS = 0;
    private final int DEFAULT_TOTAL_PAGES = 1;
    private final int DEFAULT_FROM_INDEX = 0;
    private final int DEFAULT_END_INDEX = 0;

    /**当前页下标*/
    private int pageIndex  = DEFAULT_PAGE_INDEX;
    /**当前页面显示数据量*/
    private int pageSize = DEFAULT_PAGE_SIZE;
    /**总页面数*/
    private int totalPages = DEFAULT_TOTAL_PAGES;
    /**数据库查询总记录数*/
    private long totalRecords = DEFAULT_TOTAL_RECORDS;
    /**数据库起始数据下标*/
    private int fromIndex = DEFAULT_FROM_INDEX;
    /**数据库结束数据下标*/
    private int endIndex = DEFAULT_END_INDEX;
    /**是否存在下一页*/
    private boolean hasNextPage;
    /**是否存在上一页*/
    private boolean hasPrePage;

    public Pagination(int pageIndex,int pageSize){
        this(0,pageIndex,pageSize);
    }

    public Pagination(long totalRecords,int pageIndex,int pageSize){
        init(totalRecords, pageIndex, pageSize);
    }

    private void init(long totalRecords,int pageIndex,int pageSize){

        setPageIndex(pageIndex);

        setPageSize(pageSize);

        setTotalRecords(totalRecords);

        reset();
    }

    private void reset(){
        setFromIndex((this.pageIndex - 1) * this.pageSize);

        setEndIndex(this.fromIndex + this.pageSize);

        setHasNextPage(this.pageIndex < this.totalPages);

        setHasPrePage(this.pageIndex > 1);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置当前页页号
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {

        if(0 >= pageIndex){
            pageIndex = DEFAULT_PAGE_INDEX;
        }

        this.pageIndex = pageIndex;
        //重新初始化分页参数
        reset();
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 分页页数
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if(0 > pageSize){
            pageSize = DEFAULT_PAGE_SIZE;
        }

        this.pageSize = pageSize;
        //重新初始化分页参数
        reset();
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

    /**
     * 设置查询返回的总记录数
     * @param totalRecords
     */
    public void setTotalRecords(long totalRecords) {

        if(0 > totalRecords){
            totalRecords = DEFAULT_TOTAL_RECORDS;
        }

        if(0 == totalRecords){
            this.totalPages = DEFAULT_TOTAL_PAGES;
        }else{
            this.totalPages = (int)((totalRecords - 1) / pageSize + 1);
        }

        if(0 > pageIndex){
            pageIndex = DEFAULT_PAGE_INDEX;
        }

        if(pageIndex > this.totalPages){
            pageIndex = this.totalPages + 1;
        }

        this.totalRecords = totalRecords;

        reset();
    }

    public int getFromIndex(){
        return this.fromIndex;
    }

    /**
     * 设置当前页在数据库中的起始下标
     * @param fromIndex
     */
    public void setFromIndex(int fromIndex){
        this.fromIndex = fromIndex;
    }

    public int getEndIndex(){
        return this.endIndex;
    }

    /**
     * 设置当前页在数据库中结束下标
     * @param endIndex
     */
    public void setEndIndex(int endIndex){

        if(0 > endIndex){
            endIndex = this.fromIndex;
        }

        if(fromIndex > this.totalRecords){
            endIndex = (int)this.totalRecords;
        }

        this.endIndex = endIndex;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }
}
