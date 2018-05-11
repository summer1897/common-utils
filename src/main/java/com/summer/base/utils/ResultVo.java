package com.summer.base.utils;

import com.base.enums.HttpStatus;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 *
 * @Author solstice
 * @Date 2018/05/11 10:04
 * @Description Controller层返回结果封装类
 */
public class ResultVo implements Serializable {

    private static final long serialVersionUID = 8187824885983927700L;

    /**操作成功提示信息*/
    public static final String SUCCESS_MSG = "操作成功";
    /**操作失败提示信息*/
    public static final String FAIL_MSG = "操作失败";
    /**操作失败状态码*/
    public static final int FAIL_STATUS_CODE = -1;
    /**操作成功状态码*/
    public static final int SUCCESS_STATUS_CODE = 1;


    /**相应码*/
    private int code;
    /**相应消息*/
    private String msg;
    /**相应数据*/
    private Object data;

    public static ResultVo success() {
        return new ResultVo(SUCCESS_STATUS_CODE,SUCCESS_MSG);
    }

    public static ResultVo success(Object data) {
        return new ResultVo(SUCCESS_STATUS_CODE,SUCCESS_MSG,data);
    }

    public static ResultVo success(String msg) {
        return new ResultVo(SUCCESS_STATUS_CODE,msg);
    }

    public static ResultVo success(int code) {
        return new ResultVo(code,SUCCESS_MSG);
    }

    public static ResultVo success(String msg,Object data) {
        return new ResultVo(SUCCESS_STATUS_CODE,msg,data);
    }

    public static ResultVo success(int code,Object data) {
        return new ResultVo(code,SUCCESS_MSG,data);
    }

    public static ResultVo success(int code,String msg) {
        return new ResultVo(code,msg);
    }

    public static ResultVo success(int code,String msg,Object data) {
        return new ResultVo(code,msg,data);
    }

    public static ResultVo success(HttpStatus httpStatus) {
        if (ObjectUtils.isNotNull(httpStatus)) {
            return ResultVo.success(httpStatus.getCode(),httpStatus.getMsg());
        } else {
            return ResultVo.success();
        }
    }

    public static ResultVo success(HttpStatus httpStatus,Object data) {
        if (ObjectUtils.isNotNull(httpStatus)) {
            return ResultVo.success(httpStatus.getCode(),httpStatus.getMsg(),data);
        } else {
            return ResultVo.success(data);
        }
    }

    public static ResultVo fail() {
        return new ResultVo(FAIL_STATUS_CODE,FAIL_MSG);
    }

    public static ResultVo fail(String msg) {
        return new ResultVo(FAIL_STATUS_CODE,msg);
    }

    public static ResultVo fail(int code) {
        return new ResultVo(code,FAIL_MSG);
    }

    public static ResultVo fail(int code,String msg) {
        return new ResultVo(code,msg);
    }

    public static ResultVo fail(HttpStatus httpStatus) {
        if (ObjectUtils.isNotNull(httpStatus)) {
            return ResultVo.fail(httpStatus.getCode(),httpStatus.getMsg());
        } else {
            return ResultVo.fail();
        }
    }

    public ResultVo() {
    }

    public ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(int code, String msg, Object data) {
        this(code,msg);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static boolean retVal(Integer result) {
        return ObjectUtils.isNotNull(result) && result.intValue() > 0;
    }
}
