package com.summer.base.vo;

/**
 * Created by hzduhao on 2016/7/8.
 */
public class AddressVo {
    private String province;
    private String city;
    private String road;
    private int number;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "AddressVo{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", road='" + road + '\'' +
                ", number=" + number +
                '}';
    }
}
