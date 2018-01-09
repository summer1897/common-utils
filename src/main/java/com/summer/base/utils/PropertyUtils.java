package com.summer.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author summer
 * @Date 2018/1/8
 * @Time 15:31
 * @Description 对象属性操作工具类
 */
public class PropertyUtils extends PropertyExtractUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

    public static PropertyReaderUtils setFilePath(String filePath) {
        return PropertyReaderUtils.setFilePath(filePath);
    }

    public static String get(String key) {
        return PropertyReaderUtils.get(key);
    }

    public static String get(String key,String defaultValue) {
        return PropertyReaderUtils.get(key,defaultValue);
    }

}
