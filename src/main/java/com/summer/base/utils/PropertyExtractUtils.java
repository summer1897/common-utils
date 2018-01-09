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
 * 该类已过时，可用{@link PropertyUtils}
 */
@Deprecated
public class PropertyExtractUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyExtractUtils.class);

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
            p = ReflectUtils.getFieldValueFromDomain(domain,propertyName,propertyClass);
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
        return p;
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
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
        return pros;
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
        Map<Pro,Domain> map = null;
        try{
            if(null != domain){
                map = Maps.newHashMap();
                Pro p = extractPropertyFromDomain(domain,propertyName,proClass);
                map.put(p,domain);
            }
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
        return map;
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
        Map<Pro,Domain> maps = null;
        try{
            if(CollectionUtils.isNotEmpty(domainList)){
                maps = Maps.newHashMap();
                for(Domain domain : domainList){
                    maps.putAll(PropertyExtractUtils.extractPropertyFromDomainToMap(domain,propertyName,proClass));
                }
            }
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
        return maps;
    }

    /**
     * 从实体对象集合中提取指定属性的属性值,并以该值作为Map的key,Domain作为value值,
     * 同时将key值相同的实体对象放在同一个List集合中
     * @param domainList 实体对象集合
     * @param propertyName 待导出属性名
     * @param proClass 待导出属性Class对象
     * @param <Domain>
     * @param <Pro>
     * @return
     */
    public static <Domain,Pro> Map<Pro,List<Domain>> extractPropertyFromDomainToMapList(List<Domain> domainList,String propertyName,Class<Pro> proClass){
        Map<Pro,List<Domain>> maps = null;
        try{
            if(CollectionUtils.isNotEmpty(domainList)){
                maps = Maps.newHashMap();
                for (Domain domain : domainList){
                    Pro pro = ReflectUtils.getFieldValueFromDomain(domain,propertyName,proClass);
                    List<Domain> proDomainList = maps.get(pro);
                    if(null == proDomainList){
                        proDomainList = Lists.newArrayList();
                    }
                    proDomainList.add(domain);
                    maps.put(pro,proDomainList);
                }
            }
        }catch(Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
        return maps;
    }

}
