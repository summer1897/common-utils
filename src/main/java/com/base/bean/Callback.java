package com.base.bean;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author duhao
 * @Date 2018/1/3
 * @Time 15:50
 * @Description HttpClient请求回调函数
 */
public interface Callback<S,T> {

    /**
     * 对参数{@link S}进行处理，然后返回{@link T}
     * @param s
     * @return
     */
    T doHandler(S s);

}
