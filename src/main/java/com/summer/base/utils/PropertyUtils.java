package com.summer.base.utils;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

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

    /**
     * 从实体对象集合中提取指定属性的属性值，并以该值作为Map的key,Domain作为value值,导出的
     * Map是按传入的List集合进行排序
     * @see #extractPropertyFromDomainToMap
     * @param domainList
     * @param propertyName
     * @param proClass
     * @param <Pro>
     * @param <Domain>
     * @return {@link Map<Pro,Domain>}
     */
    public static <Pro,Domain> Map<Pro,Domain> extractToSortedMap(List<Domain> domainList,
                                                                                    String propertyName,
                                                                                    Class<Pro> proClass){
        Map<Pro,Domain> maps = null;
        try{
            if(CollectionUtils.isNotEmpty(domainList)){
                maps = Maps.newLinkedHashMap();
                for(Domain domain : domainList){
                    maps.putAll(extractPropertyFromDomainToMap(domain,propertyName,proClass));
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
        return maps;
    }

    /**
     * 从实体对象集合中提取指定属性的属性值,并以该值作为Map的key,Domain作为value值,
     * 同时将key值相同的实体对象放在同一个List集合中,导出的Map是按传入的List集合进行排序
     * @param domainList
     * @param propertyName
     * @param proClass
     * @param <Pro>
     * @param <Domain>
     * @return {@link Map<Pro,List<Domain>>}
     */
    public static <Pro,Domain> Map<Pro,List<Domain>> extractToSortedMapList(List<Domain> domainList,
                                                                                    String propertyName,
                                                                                    Class<Pro> proClass) {
        Map<Pro,List<Domain>> maps = null;
        try{
            if(CollectionUtils.isNotEmpty(domainList)){
                maps = Maps.newLinkedHashMap();
                for (Domain domain : domainList){
                    Pro pro = ReflectUtils.getFieldValueFromDomain(domain,propertyName,proClass);
                    setPro(maps,domain,pro);
                }
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not extract this property: "+propertyName);
        }
        return maps;
    }

}
