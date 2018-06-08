package com.summer.base.utils;

/**
 * Created by summer on 2016/7/9.
 */
public class StringUtils {

    /**
     * 将字符串首字母转换为大写
     * @param str
     * @return @{link String}
     */
    public static String firstAlphaToUpcase(String str){
        if(null == str && str.length() <= 0){
            return null;
        }
        char ch = str.charAt(0);
        return Character.toUpperCase(ch) + str.substring(1);
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str){
        return ObjectUtils.isNull(str) || "".equals(str);
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return boolean
     */
    public static boolean isNotEmpty(String str){
        return !StringUtils.isEmpty(str);
    }

    public static boolean isAllEmpty(String...strs) {
        for (String str : strs) {
            if (StringUtils.isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyEmpty(String...strs) {
    	if (ObjectUtils.isNull(strs) && strs.length <= 0) {
    		return true;
    	}
        for (String str : strs) {
            if (StringUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllNotEmpty(String...strs) {
        return !StringUtils.isAnyEmpty(strs);
    }

    public static boolean isAnyNotEmpty(String...strs) {
        return !StringUtils.isAllEmpty(strs);
    }

    /**
     * 生成时间随机字符串
     * @param formatStr 时间格式
     * @return {@link String}
     */
    public static String generateTimeRandomStr(String formatStr) {
        if (StringUtils.isEmpty(formatStr)) {
            formatStr = DateUtils.DEFAULT_DATE_TIME_STR;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(DateUtils.getCurrenDate(formatStr)).append(nextInt(1000,9999));
        return sb.toString();
    }

    private static String nextInt(int a,int b) {
        if (a < b) {
            b += (a + 10);
        }
        return (int)(Math.random() * (b - a + 1) + a) + "";
    }

    /**
     * 字符串连接
     * @param strs
     * @return {@link String}
     */
    public static String concat(String...strs) {
        StringBuilder sb = new StringBuilder();
        if (ObjectUtils.isNotNull(strs)) {
            for (String str : strs) {
                if (StringUtils.isNotEmpty(str)){
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param open
     * @param separator
     * @param close
     * @param strs
     * @return {@link String} 返回open+separator+str1+separator+str2+separator+...strn+close形式的字符串
     */
    public static String resolve(String open,String separator,String close,String...strs) {
        StringBuilder sb = new StringBuilder(open);
        if (ObjectUtils.isNotNull(strs)) {
            for (String str : strs) {
                if (StringUtils.isNotEmpty(str)) {
                    sb.append(str).append(separator);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(close);
        return sb.toString();
    }

    public static void main(String[] args) {
        String concat = resolve("(", ",", ")", "1", "2", "3");
        System.out.println(concat);
    }
}
