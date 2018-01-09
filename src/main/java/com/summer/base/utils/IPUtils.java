package com.summer.base.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/6/20 下午3:55
 * @Description IP操作工具
 */
public class IPUtils {

    private static final String UNKNOWN = "unknown";
    private static final String X_FORWARED_FOR = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WX_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String X_REAL_IP = "X-Real-IP";

    public static String getRequestIP(HttpServletRequest request){
        String ip = request.getHeader(X_FORWARED_FOR);
        if (!validate(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (!validate(ip)) {
            ip = request.getHeader(WX_PROXY_CLIENT_IP);
        }
        if (!validate(ip)) {
            ip = request.getHeader(X_REAL_IP);
        }
        if (!validate(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        //多个代理ip的情况
        int index = ip.indexOf(",");
        if (index > 0) {
            ip = ip.substring(0,index);
        }
        return ip;
    }

    private static boolean validate(String ip) {
        if (ObjectUtils.isNull(ip) || StringUtils.isEmpty(ip) || UNKNOWN.equals(ip)) {
            return false;
        }
        return true;
    }

}
