package com.summer.base.utils;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * Created by Intellij IDEA
 *
 * @Projcet common-utils
 * @Author duhao
 * @Date 2018/1/12
 * @Time 10:48
 * @Description 封装刻画端信息
 */
public class UserAgentInfos {

    public static final String BROWSER_CHROME = "Chrome";
    public static final String BROWSER_IE = "Internet Explorer";
    public static final String BROWSER_UC = "UCBrowser：UC";
    public static final String BROWSER_FIREFOX = "Fire Fox";
    public static final String BROWSER_OPRRA = "Opera";
    public static final String BROWSER_SOGOU = "Sogou Explorer";

    private static UserAgent userAgent = null;

    private UserAgentInfos() {}

    public static UserAgentInfos resolve(String userAgentStr) {
        if (StringUtils.isEmpty(userAgentStr)) {
            return null;
        }
        userAgent = UserAgent.parseUserAgentString(userAgentStr);
        return new UserAgentInfos();
    }

    /**
     *
     * @return {@link String} 返回浏览器名称
     */
    public static String browser() {
        return userAgent.getBrowser().getName();
    }

    /**
     *
     * @return {@link String} 返回浏览器版本
     */
    public static String browserVersion() {
        return userAgent.getBrowserVersion().getVersion();
    }

    public static String browserMajorVersion() {
        return userAgent.getBrowserVersion().getMajorVersion();
    }

    public static String browserMinorVersion() {
        return userAgent.getBrowserVersion().getMinorVersion();
    }

    /**
     *
     * @return {@link String} 返回操作系统名
     */
    public static String osName() {
        return userAgent.getOperatingSystem().getName();
    }

    /**
     *
     * @return {@link String} 返回客户端设备信息
     */
    public static String deviceName() {
        return userAgent.getOperatingSystem().getDeviceType().getName();
    }

    /**
     *
     * @return {@link String} 返回客户端操作系统制造厂商
     */
    public static String manufactureName() {
        return userAgent.getOperatingSystem().getManufacturer().getName();
    }
}
