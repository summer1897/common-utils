package com.summer.base.utils;

import com.alibaba.fastjson.JSON;
import com.base.bean.Callback;
import com.google.common.collect.Lists;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author duhao
 * @Date 2018/1/3
 * @Time 15:16
 * @Description 后台通过URL请求工具类
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * Get请求
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            logger.warn("请求URL为空");
            return result;
        }
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            if (ObjectUtils.isNotNull(response)) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            logger.error("请求出错");
            e.printStackTrace();
        } finally {
            closeResponse(response);
        }
        return result;
    }

    /**
     * Get请求，并调用{@link Callback}回调接口
     * @param url
     * @param callback
     * @param <T>
     * @return
     */
    public static <S,T> T sendGet(String url,Callback<S,T> callback) {
        String result = sendGet(url);
        return callback.doHandler((S)result);
    }

    /**
     * 带参数的Get请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String sendGet(String url, Map<String,String> params) throws IOException {
        String result = null;
        if (StringUtils.isNotEmpty(url) && ObjectUtils.isNotEmpty(params)) {
            List<NameValuePair> ps = Lists.newArrayList();
            for (String key : params.keySet()) {
                String val = params.get(key);
                if (StringUtils.isNotEmpty(val)) {
                    ps.add(new BasicNameValuePair(key,val));
                }
            }
            if (ObjectUtils.isNotEmpty(ps)) {
               String formatParam = EntityUtils.toString(new UrlEncodedFormEntity(ps, Consts.UTF_8));
               result = sendGet(url + "?" + formatParam);
            }
        }
        return result;
    }

    /**
     * 带参数及回调处理的Get请求
     * @param url
     * @param params
     * @param callback
     * @param <S>
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <S,T> T sendGet(String url,Map<String,String> params,Callback<S,T> callback) throws IOException {
        String result = sendGet(url,params);
        return callback.doHandler((S)result);
    }

    private static String doPost(String url,UrlEncodedFormEntity urlEncodedFormEntity) {
        String result = null;
        if (StringUtils.isEmpty(url)) {
            logger.warn("Post请求URL为空");
        } else {
            HttpPost post = new HttpPost(url);
            if (ObjectUtils.isNotNull(urlEncodedFormEntity)) {
                post.setEntity(urlEncodedFormEntity);
            }
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = null;
                if (ObjectUtils.isNotNull(response)) {
                    entity = response.getEntity();
                }
                if (ObjectUtils.isNotNull(entity)) {
                    result = EntityUtils.toString(entity);
                }
            } catch (IOException e) {
                logger.error("Post请求出错");
                e.printStackTrace();
            } finally {
                closeResponse(response);
            }
        }

        return result;
    }

    /**
     * Post请求
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        return doPost(url,null);
    }

    /**
     * 带回调处理的Post请求
     * @param url
     * @param callback
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> T sendPost(String url,Callback<S,T> callback) {
        return callback.doHandler((S)sendGet(url));
    }

    /**
     * 带参数的Post请求
     * @param url
     * @param params
     * @return
     */
    public static String sendPost(String url,Map<String,String> params) {
        String result = null;
        if (ObjectUtils.isNotEmpty(params)) {
            List<NameValuePair> ps = Lists.newArrayList();
            for (String key : params.keySet()) {
                String val = params.get(key);
                if (StringUtils.isNotEmpty(val)) {
                    ps.add(new BasicNameValuePair(key,val));
                }
            }

            if (ObjectUtils.isNotEmpty(ps)) {
                result = doPost(url,new UrlEncodedFormEntity(ps,Consts.UTF_8));
            }
        }
        return result;
    }

    /**
     * 带回调和参数的Post请求
     * @param url
     * @param params
     * @param callback
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> T sendPost(String url,Map<String,String> params,Callback<S,T> callback) {
        return callback.doHandler((S)sendPost(url,params));
    }

    private static void closeResponse(CloseableHttpResponse response) {
        if (ObjectUtils.isNotNull(response)) {
            try {
                response.close();
            } catch (IOException e) {
                logger.error("关闭响应流出错");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String url = "http://api.data.cma.cn:8090/api?userId=514901973386keU14&pwd=lM707dx&" +
        "dataFormat=json&interfaceId=getSurfEleByTimeRangeAndStaID&" +
                "dataCode=SURF_CHN_MUL_HOR&timeRange=[20180101030000,20180103030000]&staIDs=57807&" +
                "elements=Station_Id_C,Year,Mon,Day,Hour,TEM";
        Object restul = sendGet(url, new Callback<String, Object>() {
            public Object doHandler(String s) {
                return JSON.toJSONString(JSON.parseObject(s),true);
            }
        });
        System.out.println("result: " + restul);
    }

}
