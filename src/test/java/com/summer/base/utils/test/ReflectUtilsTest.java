package com.summer.base.utils.test;

import com.summer.base.utils.ReflectUtils;
import com.summer.base.vo.AddressVo;
import com.summer.base.vo.UserVo;
import org.junit.Test;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 16/11/22 下午2:03
 * @Description
 */
public class ReflectUtilsTest {

    @Test
    public void getFieldValueFromDomainTest(){
        UserVo userVo = new UserVo();
        userVo.setName("summer");
        userVo.setAge(28);
        userVo.setEmail("summer@sina.com");
        userVo.setPhone("15268528314");
        AddressVo addressVo = new AddressVo();
        addressVo.setProvince("浙江省");
        addressVo.setCity("杭州市");
        addressVo.setRoad("浙大路");
        addressVo.setNumber(38);
        userVo.setAddressVo(addressVo);

        String name = ReflectUtils.getFieldValueFromDomain(userVo,"name",String.class);
        System.out.println("obtain the field value of name is: " + name);

        AddressVo addressVo1 = ReflectUtils.getFieldValueFromDomain(userVo,"addressVo",AddressVo.class);
        System.out.println("obtain the address value is: " + addressVo1);
    }

}
