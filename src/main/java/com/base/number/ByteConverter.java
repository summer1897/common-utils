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
public class ByteConverter implements Converter<String,Byte> {

    public Byte convert(String s) throws NumberFormatException {
        return Byte.parseByte(s);
    }

}
