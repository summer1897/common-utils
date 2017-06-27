package com.summer.base.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/6/27 下午5:06
 * @Description Controller层返回结果封装类
 */
public class ResultVo implements Serializable{

    private static final long serialVersionUID = -6583684461564442731L;

    /**返回状态码，1：success，0：error*/
    private int code;
    /**错误消息*/
    private String message;
    /**存放返回给前端的数据集*/
    private Map<String,Object> result = new HashMap<String,Object>();

    public ResultVo(){}

    public ResultVo(int code,String message){
        this.code = code;
        this.message = message;
    }

    public ResultVo(int code,String message,Map<String,Object> result) {
        this(code,message);
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    /**
     * 添加数据
     * @param key
     * @param value
     */
    public void addData(String key,Object value) {
        result.put(key,value);
    }
}
