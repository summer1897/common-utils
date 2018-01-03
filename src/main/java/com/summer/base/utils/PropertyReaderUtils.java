package com.summer.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author duhao
 * @Date 2018/1/3
 * @Time 14:19
 * @Description Property解析工具类 ，用于读取properties属性文件
 */
public class PropertyReaderUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertyReaderUtils.class);
    private static Properties properties;

    private PropertyReaderUtils() {}

    private synchronized static void load(String filePath) {
        properties = new Properties();
        InputStream in = null;
        try {
            in = PropertyReaderUtils.class.getClassLoader().getResourceAsStream(filePath);
            properties.load(in);
        } catch (FileNotFoundException e) {
            logger.error("没有找到对应路径的读取属性文件");
        } catch (IOException e) {
            logger.error("属性文件读取错误");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("属性文件流关闭异常");
            }
        }
    }

    public static PropertyReaderUtils setFilePath(String filePath) {
        load(filePath);
        return new PropertyReaderUtils();
    }

    public static String get(String key) {
        if (ObjectUtils.isNull(properties)) {
            throw new RuntimeException("请先调用PropertyReaderUtils.setFilePath()方法设置文件读取路径");
        }
        return properties.getProperty(key);
    }

    public static String get(String key,String defaultValue) {
        if (ObjectUtils.isNull(properties)) {
            throw new RuntimeException("请先调用PropertyReaderUtils.setFilePath()方法设置文件读取路径");
        }
        return properties.getProperty(key,defaultValue);
    }

}
