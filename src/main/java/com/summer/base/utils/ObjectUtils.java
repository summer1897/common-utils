package com.summer.base.utils;


import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * Author duhao
 * Date 2016/7/12
 * Time 20:47
 */
public class ObjectUtils {

    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断集合对象是否全部不为Null
     * @param collection
     * @return Booelan,集合中对象只要有一个为Null，返回false
     */
    public static boolean isAllListElementNotNull(Collection<? extends Object> collection) {
        if (ObjectUtils.isEmpty(collection)) {
            return false;
        }

        for (Object o : collection) {
            if (ObjectUtils.isNull(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判读集合中是否存在一个非Null元素
     * @param collection
     * @return Boolean,集合中对象只要有一个为非Null，则返回true
     */
    public static boolean isAnyListElementNotNull(Collection<? extends Object> collection) {
        for (Object o : collection) {
            if (ObjectUtils.isNotNull(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断Map集合的Values集合是否存在Null值
     * @param map
     * @return booleam,只要Map集合中的values集合中的对象有一个为Null,返回false
     */
    public static boolean isAllMapValuesNotNull(Map<? extends Object,? extends Object> map) {
        if (ObjectUtils.isEmpty(map)) {
            return false;
        }
        return ObjectUtils.isAllListElementNotNull(map.values());
    }

    public static boolean isAnyMapValuesNotNull(Map<? extends Object,? extends Object> map) {
        if (ObjectUtils.isEmpty(map)) {
            return false;
        }
        return ObjectUtils.isAnyListElementNotNull(map.values());
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return true为空，false不为空
     */
    @Deprecated
    public static boolean isEmptyList(Collection<?> collection) {
        if (null == collection || collection.size() <= 0)
            return true;
        return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return false为空，true不为空
     */
    @Deprecated
    public static boolean isNotEmptyList(Collection<?> collection) {
        return !ObjectUtils.isEmptyList(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        if (null == collection || collection.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return !ObjectUtils.isNotEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?,?> map) {
        if (null == map || map.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Map<?,?> map) {
        return !ObjectUtils.isNotEmpty(map);
    }

    public static boolean isEmpty(Object[] objs) {
        if (ObjectUtils.isNull(objs) || objs.length <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object[] objs) {
        return !ObjectUtils.isEmpty(objs);
    }

}
