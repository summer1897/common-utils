package com.summer.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 16/11/22 下午1:52
 * @Description 通过Java反射机制访问实体对象工具
 */
public class ReflectUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ReflectUtils.class);
    private static final String PREFIX_GET = "get";

    /**
     * 获取实体对象Domain指定属性字段的值
     * @param proClass Domain Class
     * @param propertyName 实体对象属性字段名
     * @param <Pro> 待返回属性字段数据类型
     * @return
     */
    public static <Domain,Pro> Pro getFieldValueFromDomain(Domain domain,String propertyName,Class<Pro> proClass){
        if(null == domain || null == proClass || null == propertyName)
            return null;
        Pro pro = null;
        try{
            Method method = domain.getClass().getMethod(PREFIX_GET + StringUtils.firstAlphaToUpcase(propertyName));
            pro = (Pro)method.invoke(domain);
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not obtain this property value: "+propertyName);
        }
        return pro;
    }

}
