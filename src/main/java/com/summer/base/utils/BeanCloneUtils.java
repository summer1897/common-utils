package com.summer.base.utils;

import com.alibaba.fastjson.JSON;
import com.base.pagination.PaginationResult;
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
     * @return @{link To}
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
    }

    /**
     * 批量对象克隆
     * @param fromList 原对象实体集合
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return @{link To}
     */
    public static <From,To> List<To> clone(List<From> fromList, Class<From> fromClass, Class<To> toClass){
        try{
            List<To> toLists = Lists.newArrayList();

            if (!ObjectUtils.isEmptyList(fromList)) {
                return toLists;
            }

            for(From from : fromList){
                toLists.add(BeanCloneUtils.clone(from,fromClass,toClass));
            }
            return toLists;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
    }

    /**
     * 克隆 @{link PaginationResult<T>}对象
     * @param fromPaginationResult
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return @{link PaginationResult<To>}
     */
    public static <From,To> PaginationResult<To> clone(PaginationResult<From> fromPaginationResult,Class<From> fromClass,Class<To> toClass){
        try{
            PaginationResult<To> toPaginationResult = new PaginationResult<To>();
            toPaginationResult.setQuery(fromPaginationResult.getQuery());

            List<From> fromList = fromPaginationResult.getResult();

            if(CollectionUtils.isNotEmpty(fromList)){
                toPaginationResult.setResult(BeanCloneUtils.clone(fromList,fromClass,toClass));
            }

            return toPaginationResult;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
    }

    /**
     * 深度克隆 @{link PaginationResult<T>}对象
     * @param fromPaginationResult
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return   @{link PaginationResult<To>}
     */
    public static <From,To> PaginationResult<To> deepClone(PaginationResult<From> fromPaginationResult,Class<From> fromClass,Class<To> toClass){
        try{
            PaginationResult<To> toPaginationResult = new PaginationResult<To>();
            toPaginationResult.setQuery(fromPaginationResult.getQuery());

            List<From> fromList = fromPaginationResult.getResult();

            if(CollectionUtils.isNotEmpty(fromList)){
                toPaginationResult.setResult(BeanCloneUtils.deepClone(fromList,fromClass,toClass));
            }

            return toPaginationResult;
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
    }

    /**
     * 深度克隆
     * @param from 原对象实体
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return @{link To}
     */
    public static <From,To> To deepClone(From from , Class<From> fromClass, Class<To> toClass){
        To to = null;
        try{
            if(null == from){
                return null;
            }
            String jsonString = JSON.toJSONString(from);

            to = JSON.parseObject(jsonString,toClass);
        }catch(Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
//            throw new RuntimeException("can not clone");
        }
        return to;
    }

    /**
     * 批量深度克隆
     * @param fromList 原对象实体集合
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     * @return @{link To}
     */
    public static <From,To> List<To> deepClone(List<From> fromList, Class<From> fromClass, Class<To> toClass){
        List<To> toList = Lists.newArrayList();
        try{
            for (From from : fromList){
                toList.add(BeanCloneUtils.deepClone(from,fromClass,toClass));
            }
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
//            throw  new RuntimeException("can not clone");
        }
        return toList;
    }

    /**
     *  单一对象克隆
     * @param from 原对象实体
     * @param to 克隆后对象
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     */
    public static <From,To> void clone(From from,To to,Class<From> fromClass, Class<To> toClass){
        try{

            if(null == from){
                return;
            }
            if(null != to){
                to = toClass.newInstance();
            }
            BeanCopier beanCopier = BeanCopier.create(fromClass,toClass,false);
            beanCopier.copy(from,to,null);
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
    }

    /**
     * 批量对象克隆
     * @param fromList 原对象集合
     * @param toList 克隆对象集合
     * @param fromClass 原对象Class对象
     * @param toClass 待转换对象的Class对象
     * @param <From>
     * @param <To>
     */
    public static <From,To> void clone(List<From> fromList, List<To> toList, Class<From> fromClass, Class<To> toClass){
        try{
            if(CollectionUtils.isEmpty(fromList) || CollectionUtils.isEmpty(toList))
                return;
            for(From from : fromList){
                To newTo = null;
                newTo = toClass.newInstance();
                BeanCloneUtils.clone(from,newTo,fromClass,toClass);
                toList.add(newTo);
            }
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException("can not clone");
        }
    }
}
