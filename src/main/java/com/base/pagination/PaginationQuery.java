package com.base.pagination;

/**
 * Created by IntelliJ IDEA.
 * Author: hzduhao
 * Date: 2016/7/12
 * Time: 16:22
 */
public class PaginationQuery extends Pagination{

    private String sortedPropertyName = null;

    public PaginationQuery(long totalRecords,int pageIndex, int pageSize){
        super(totalRecords, pageIndex, pageSize);
    }

    public PaginationQuery(long totalRecords,int pageIndex, int pageSize, String sortedPropertyName){
        super(totalRecords, pageIndex, pageSize);
        setSortedPropertyName(sortedPropertyName);
    }

    public String getSortedPropertyName(){
        return this.sortedPropertyName;
    }

    public void setSortedPropertyName(String sortedPropertyName){
        this.sortedPropertyName = sortedPropertyName;
    }

}

