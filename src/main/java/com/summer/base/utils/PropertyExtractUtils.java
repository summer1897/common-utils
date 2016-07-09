package com.summer.base.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by summer on 2016/7/9.
 * 对象实体属性导出工具类
 */
public class PropertyExtractUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyExtractUtils.class);

    private static final String PREFIX_GET = "get";

    /**
     * 获取实体对象中指定名称的属性值
     * @param domain 实体对象
     * @param propertyName 待导出属性名
     * @param propertyClass 待导出属性Class对象
     * @param <Domain>
     * @param <Pro>
     * @return
     */
    public static <Domain,Pro> Pro extractPropertyFromDomain(Domain domain,String propertyName,Class<Pro> propertyClass){
        Pro p = null;
        try{
            Method method = domain.getClass().getMethod(PREFIX_GET + StringUtils.firstAlphaToUpcase(propertyName));
            p = (Pro)method.invoke(domain);
            return p;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
    }

    /**
     * 获取实体集合对象中指定名称的所有属性值
     * @param objectList 实体对象集合
     * @param propertyName 待导出属性名
     * @param proClass 待导出属性Class对象
     * @param <Domain>
     * @param <Pro>
     * @return
     */
    public static <Domain,Pro> List<Pro> extractPropertyFromDomain(List<Domain> objectList, String propertyName, Class<Pro> proClass){
        List<Pro> pros = Lists.newArrayList();
        try{
            if(CollectionUtils.isEmpty(objectList))
                return pros;
            for(Object object : objectList){
                pros.add(PropertyExtractUtils.extractPropertyFromDomain(object,propertyName,proClass));
            }
            return pros;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
    }

    /**
     * 从实体对象中提取指定属性的属性值，并以该值作为Map的key,Domain作为value值
     * @param domain 实体对象
     * @param propertyName 待导出属性名
     * @param proClass 待导出属性Class对象
     * @param <Domain>
     * @param <Pro>
     * @return
     */
    public static <Domain,Pro> Map<Pro,Domain> extractPropertyFromDomainToMap(Domain domain, String propertyName, Class<Pro> proClass){
        try{
            Map<Pro,Domain> map = null;
            if(null != domain){
                map = Maps.newHashMap();
                Pro p = extractPropertyFromDomain(domain,propertyName,proClass);
                map.put(p,domain);
            }
            return map;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
    }

    /**
     * 从实体对象集合中提取指定属性的属性值，并以该值作为Map的key,Domain作为value值
     * @param domainList 实体对象集合
     * @param propertyName 待导出属性名
     * @param proClass 待导出属性Class对象
     * @param <Domain>
     * @param <Pro>
     * @return
     */
    public static <Domain,Pro> Map<Pro,Domain> extractPropertyFromDomainToMap(List<Domain> domainList,String propertyName,Class<Pro> proClass){
        try{
            Map<Pro,Domain> maps = null;
            if(CollectionUtils.isNotEmpty(domainList)){
                maps = Maps.newHashMap();
                for(Domain domain : domainList){
                    maps.putAll(PropertyExtractUtils.extractPropertyFromDomainToMap(domain,propertyName,proClass));
                }
            }
            return maps;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
    }
}
