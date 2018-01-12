package com.summer.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author duhao
 * @Date 2017/12/25
 * @Time 15:24
 * @Description 日期操作工具类
 */
public class DateUtils {

    /**
     * 默认日期时间字符串
     */
    public static final String DEFAULT_DATE_TIME_STR = "yyyyMMddHHmmss";

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

        return DateUtils.getFormat(format).format(new Date());
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
        return DateUtils.getFormat(format).format(new Date());
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
        return DateUtils.getFormat(format).format(new Date());
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
        return DateUtils.getFormat(format).format(date);
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
                date = DateUtils.getFormat(DEFAULT_DATE_TIME_FORMAT).parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    private static Integer get(Date date,Integer field) {
        Integer result = -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (ObjectUtils.isNotNull(result)) {
            result = calendar.get(field);
        }
        return result;
    }

    public static Integer getDayOfMonth(Date date) {
        return get(date,Calendar.DAY_OF_MONTH);
    }

    public static Integer getDayOfWeek(Date date) {
       return get(date,Calendar.DAY_OF_WEEK);
    }

    public static Integer getDayOfWeekInMonth(Date date) {
        return get(date,Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    public static Integer getDayOfYear(Date date) {
        return get(date,Calendar.DAY_OF_YEAR);
    }

    public static Integer getMonthOfYea(Date date) {
        return get(date,Calendar.MONTH);
    }

    public static Integer getYear(Date date) {
        return get(date,Calendar.YEAR);
    }

    public static Integer getHour(Date date) {
        return get(date,Calendar.HOUR);
    }

    public static Integer getHourOfDay(Date date) {
        return get(date,Calendar.HOUR_OF_DAY);
    }

    public static Integer getMinute(Date date) {
        return get(date,Calendar.MINUTE);
    }

    public static Integer getSecond(Date date) {
        return get(date,Calendar.SECOND);
    }

    public static Integer getMilliSencond(Date date) {
        return get(date,Calendar.MILLISECOND);
    }

    public static void main(String[] args) {
        System.out.println("get hour: " + getHour(new Date()));
        System.out.println("get hour of day: " + getHourOfDay(new Date()));
    }

}
