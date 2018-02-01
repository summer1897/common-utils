package com.summer.base.utils;

import com.base.inter.Converter;
import com.base.number.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

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
     * 将字符串数据转换为特定类型List集合
     * @param data 待解析数据源
     * @param separator 数据解析分隔符，以该数据分隔符进行分隔
     * @return {@link List<To>}
     */
    public static <To> List<To> toList(String data,String separator,Class<To> toClass) {
        List<To> tos = Lists.newArrayList();
        if (StringUtils.isNotEmpty(data)) {
            if (StringUtils.isNotEmpty(separator)) {
                separator = DEFAULT_SEPARATOR;
            }
            String[] elements = data.split(separator);
            if (ObjectUtils.isNotNull(elements) && elements.length > 0) {
                for (String element : elements) {
                    tos.add(parse(element,toClass));
                }
            }
        }
        return tos;
    }

    private static <To> To parse(String data,Class<To> toClass) {
        To to = null;
        if (ObjectUtils.isNotNull(toClass)) {
            Converter<String,To> converter = baseNumberConverters.get(toClass);
            to = converter.convert(data);
        }
        return to;
    }

    public static void main(String[] args) {
        String data = "1,2,3,4,5,6,7,8,9,10";
        List<Integer> numbers = toList(data, ",", Integer.class);
        System.out.println("number:" + numbers);
    }

}
