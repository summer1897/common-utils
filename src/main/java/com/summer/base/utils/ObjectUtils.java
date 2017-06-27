package com.summer.base.utils;

import java.util.Collection;
import java.util.List;

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

    /**
     * 判断集合是否为空
     * @param list
     * @return
     */
    public static boolean isEmptyList(Collection<?> list) {
        if(null == list || list.size() <= 0)
            return true;
        return false;
    }
}
