package com.base.pagination;

import java.util.List;

/**
 * Created by summer on 16/7/9.
 */
public class PaginationResult<T> {

    private PaginationQuery query;

    private List<T> result;

    public PaginationResult(){}

    public PaginationResult(PaginationQuery query,List<T> result){
        this.query = query;
        this.result = result;
    }

    public void setQuery(PaginationQuery query){
        this.query = query;
    }
    public PaginationQuery getQuery(){
        return this.query;
    }

    public void setResult(List<T> result){
        this.result = result;
    }
    public List<T> getResult(){
        return this.result;
    }


}
