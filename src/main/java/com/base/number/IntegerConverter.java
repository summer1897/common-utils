package com.base.number;

import com.base.inter.Converter;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author summer
 * @Date 2018/2/1
 * @Time 17:07
 * @Description
 */
public class IntegerConverter implements Converter<String,Integer> {

    public Integer convert(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }
    
}
