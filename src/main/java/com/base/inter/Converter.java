package com.base.inter;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author solstice
 * @Date 2018/2/1
 * @Time 17:04
 * @Description 数据转换接口，用于
 */
public interface Converter<Source,To> {

    /**
     * 将{@link Source}数据类型转换
     * 为{@link To}数据类型
     * @param source
     * @return {@link To}
     */
    To convert(Source source);

}
