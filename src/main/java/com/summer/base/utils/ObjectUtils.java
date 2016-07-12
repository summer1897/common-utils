package com.summer.base.utils;

/**
 * Created by IntelliJ IDEA
 * Author summer
 * Date 2016/7/12
 * Time 20:47
 */
public class ObjectUtils {

    public static boolean isNull(Object obj){
        return null == obj;
    }

    public static boolean isNotNull(Object obj){
        return !isNull(obj);
    }
}
