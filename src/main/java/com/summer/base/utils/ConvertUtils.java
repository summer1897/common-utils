package com.summer.base.utils;

import com.base.inter.Converter;
import com.base.number.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author summer
 * @Date 2018/2/1
 * @Time 16:26
 * @Description 数据类型转换工具
 */
public class ConvertUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    private static final String DEFAULT_SEPARATOR = ",";

    private static Map<Class<?>,Converter> baseNumberConverters = Maps.newHashMap();

    static {
        baseNumberConverters.put(Byte.class,new ByteConverter());
        baseNumberConverters.put(Short.class,new ShortConverter());
        baseNumberConverters.put(Integer.class,new IntegerConverter());
        baseNumberConverters.put(Long.class,new LongConverter());
        baseNumberConverters.put(Float.class,new FloatConverter());
        baseNumberConverters.put(Double.class,new DoubleConverter());
        baseNumberConverters.put(Boolean.class,new BooleanConverter());
    }

    /**
     * 将{@link Source}数据类型转换为{@link To}数据类型
     * @param source
     * @param converter
     * @param <Source>
     * @param <To>
     * @return {@link To}
     */
    public static <Source,To> To convert(Source source,Converter<Source,To> converter) {
        To to = null;
        if (ObjectUtils.isNotNull(source) && ObjectUtils.isNotNull(converter)) {
            to = converter.convert(source);
        }
        return to;
    }

    /**
     *
     * @param data
     * @param toClass
     * @param <To>
     * @return {@link To}
     */
    public static <To> To convert(String data,Class<To> toClass) {
        To to = null;
        if (ObjectUtils.isNotNull(toClass)) {
            Converter<String,To> converter = baseNumberConverters.get(toClass);
            to = convert(data,converter);
        }
        return to;
    }

    /**
     *
     * @param dataList
     * @param toClass
     * @param <To>
     * @return {@link List<To>}
     */
    public static <To> List<To> convert(List<String> dataList,Class<To> toClass) {
        List<To> toList = Lists.newArrayList();
        if (ObjectUtils.isNotEmpty(dataList)) {
            dataList.forEach(data -> {
                toList.add(convert(data,toClass));
            });
        }
        return toList;
    }

    /**
     *
     * @param datas
     * @param toClass
     * @param <To>
     * @return {@link List<To>}
     */
    public static <To> List<To> convert(String []datas,Class<To> toClass) {
        List<To> toList = Lists.newArrayList();
        if (ObjectUtils.isNotEmpty(datas)) {
            toList.addAll(convert(Arrays.asList(datas),toClass));
        }
        return toList;
    }

    private static String[] separate(String data,String separator) {
        String []elements = null;
        if (StringUtils.isNotEmpty(data)) {
            if (StringUtils.isNotEmpty(separator)) {
                separator = DEFAULT_SEPARATOR;
            }
            elements = data.split(separator);
        }
        return elements;
    }

    /**
     * 将字符串数据转换为特定类型List集合
     * @param data 待解析数据源
     * @param separator 数据解析分隔符，以该数据分隔符进行分隔
     * @return {@link List<To>}
     */
    public static <To> List<To> toList(String data,String separator,Class<To> toClass) {
        List<To> tos = Lists.newArrayList();
        String[] elements = separate(data, separator);
        if (ObjectUtils.isNotNull(elements) && elements.length > 0) {
            for (String element : elements) {
                tos.add(convert(element,toClass));
            }
        }
        return tos;
    }

    /**
     * 动态指定转换器，将字符串转换为指定的数据
     * @param data
     * @param separator
     * @param converter
     * @param <To>
     * @return {@link List<To>}
     */
    public static <To> List<To> toList(String data,String separator,Converter<String,To> converter) {
        List<To> toList = Lists.newArrayList();
        String[] elements = separate(data, separator);
        if (ObjectUtils.isNotEmpty(elements)) {
            for (String element : elements) {
                toList.add(converter.convert(element));
            }
        }
        return toList;
    }

    /**
     *
     * @param data
     * @param separator
     * @param toClass
     * @param <To>
     * @return {@link Set<To>}
     */
    public static <To> Set<To> toSet(String data,String separator,Class<To> toClass) {
        Set<To> toSet = Sets.newHashSet();
        List<To> toList = toList(data, separator, toClass);
        if (ObjectUtils.isNotEmpty(toList)) {
            toSet.addAll(Sets.newHashSet(toList));
        }
        return toSet;
    }



    public static void main(String[] args) {
        String numberStr = "1,2,3,4,5,6,7,8,9,8,7,6";
        Set<Integer> number = toSet(numberStr, ",", Integer.class);
        System.out.println("number: " + number);
    }

}
