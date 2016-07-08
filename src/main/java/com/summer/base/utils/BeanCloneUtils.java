package com.summer.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

/**
 * Created by hzduhao on 2016/7/8.
 *
 * Java bean copy utils
 */
public class BeanCloneUtils {

    private static final Logger LOG = LoggerFactory.getLogger(BeanCloneUtils.class);

    /**
     *  单一对象克隆
     * @param from 原对象实体
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return
     */
    public static <From,To> To clone(From from,Class<From> fromClass,Class<To> toClass){
        try{
            if(null == from){
                return null;
            }
            To newTo = null;
            newTo = toClass.newInstance();

            BeanCopier beanCopier = BeanCopier.create(fromClass,toClass,false);
            beanCopier.copy(from,newTo,null);
            return newTo;
        }catch(Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
//        return null;
    }

    /**
     * 批量对象克隆
     * @param fromList 原对象实体集合
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return
     */
    public static <From,To> List<To> clone(List<From> fromList, Class<From> fromClass, Class<To> toClass){
        try{
            List<To> toLists = Lists.newArrayList();

            for(From from : fromList){
                toLists.add(BeanCloneUtils.clone(from,fromClass,toClass));
            }
            return toLists;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
//        return null;
    }

    /**
     * 深度克隆
     * @param from 原对象实体
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return
     */
    public static <From,To> To deepClone(From from , Class<From> fromClass, Class<To> toClass){
        try{
            if(null == from){
                return null;
            }
            String jsonString = JSON.toJSONString(from);

            return JSON.parseObject(jsonString,toClass);
        }catch(Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
    }

    /**
     * 批量深度克隆
     * @param fromList 原对象实体集合
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return
     */
    public static <From,To> List<To> deepClone(List<From> fromList, Class<From> fromClass, Class<To> toClass){
        try{
            List<To> toList = Lists.newArrayList();
            for (From from : fromList){
                toList.add(BeanCloneUtils.deepClone(from,fromClass,toClass));
            }
            return toList;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw  new RuntimeException("can not clone");
        }
    }
}