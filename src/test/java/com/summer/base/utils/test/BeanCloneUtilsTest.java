package com.summer.base.utils.test;

import com.google.common.collect.Lists;
import com.summer.base.domain.Address;
import com.summer.base.domain.User;
import com.summer.base.utils.BeanCloneUtils;
import com.summer.base.vo.UserVo;

import java.util.List;

/**
 * Created by hzduhao on 2016/7/8.
 */
public class BeanCloneUtilsTest {
    public static void main(String[] args) {
        List<User> userList = Lists.newArrayList();

        Address address = new Address();
        address.setProvince("浙江省");
        address.setCity("杭州市");
        address.setStreet("西湖区");
        address.setRoad("浙大路");
        address.setNumber(38);

        User summer = new User();
        summer.setId(123091L);
        summer.setName("summer");
        summer.setSex("male");
        summer.setAge(28);
        summer.setPhone("15268528314");
        summer.setEmail("summer@sina.com");
        summer.setAddress(address);

        User solstice = new User();
        solstice.setId(123091L);
        solstice.setName("solstice");
        solstice.setSex("fmale");
        solstice.setAge(27);
        solstice.setPhone("15268528316");
        solstice.setEmail("solstice@sina.com");
        solstice.setAddress(address);

        userList.add(summer);
        userList.add(solstice);

        List<UserVo> userVoList = BeanCloneUtils.deepClone(userList,User.class,UserVo.class);

        for(UserVo userVo : userVoList){
            System.out.println(userVo);
            System.out.println("------------------------------------------");
        }
    }
}
