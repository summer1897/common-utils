package com.summer.base.utils;

/**
 * Created by summer on 2016/7/9.
 */
public class StringUtils {

    /**
     * 将字符串首字母转换为大写
     * @param str
     * @return
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
     * @return
     */
    public static boolean isEmpty(String str){
        return ObjectUtils.isNotNull(str) && "".equals(str);
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        return ObjectUtils.isNotNull(str) && !"".equals(str);
    }
}
