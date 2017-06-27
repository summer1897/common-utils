package com.summer.base.utils;

import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/6/27 下午2:25
 * @Description 序列化工具
 */
public class SerializeUtils {

    /**
     * 对象序列化操作
     * @param object 待序列化对象
     * @return 序列化字符串{@java.lang.String}
     */
    public static String serialize(Object object) {
        String objStr = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            objStr = Base64Utils.encodeToString(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objStr;
    }

    /**
     * 反序列化操作
     * @param objStr 待解码字符串
     * @param <Obj>
     * @return <Obj>
     */
    public static <Obj> Obj deserilize(String objStr) {
        Obj obj = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64Utils.decodeFromString(objStr));
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Obj)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

}
