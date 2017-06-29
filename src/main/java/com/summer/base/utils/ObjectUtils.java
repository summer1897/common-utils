package com.summer.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Author summer
 * Date 2016/7/12
 * Time 20:47
 */
public class ObjectUtils {

    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "hh:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认日期（含时间）格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return true为空，false不为空
     */
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
    public static boolean isNotEmptyList(Collection<?> collection) {
        return !ObjectUtils.isEmptyList(collection);
    }

    private static SimpleDateFormat getFormat(String format) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(format);
    }

    /**
     * 获取当前时间（不包含日期)
     *
     * @param format 时间显示格式
     * @return 时间字符串，如13:34:38
     */
    public static String getCurrenTime(String format) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_TIME_FORMAT;
        }

        return ObjectUtils.getFormat(format).format(new Date());
    }

    /**
     * 获取当前日期（不包含时间)
     * @param format 日期显示格式
     * @return 日期字符串，如2017-06-25
     */
    public static String getCurrenDate(String format) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_DATE_FORMAT;
        }
        return ObjectUtils.getFormat(format).format(new Date());
    }

    /**
     * 获取当前日期（含时间)
     * @param format 日期显示格式
     * @return 日期字符串，如2017-06-25 13:34:38
     */
    public static String getCurrenDateAndTime(String format) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        return ObjectUtils.getFormat(format).format(new Date());
    }

    /**
     * 格式化日期
     * @param date
     * @param format 日期格式，如：yyyy-MM-dd hh:mm:ss
     * @return 返回格式化后的日期,如2017-06-29 20:20:23
     */
    public static String formatDate(Date date,String format) {
        if (ObjectUtils.isNull(date)) {
            date = new Date();
        }
        return ObjectUtils.getFormat(format).format(date);
    }

    /**
     * 解析时间字符串
     * @param dateStr 时间字符串
     * @return {@see java.util.Date}
     */
    public static Date pareseDate(String dateStr) {
        Date date = null;
        if (StringUtils.isNotEmpty(dateStr)) {
            try {
                date = ObjectUtils.getFormat(DEFAULT_DATE_TIME_FORMAT).parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
