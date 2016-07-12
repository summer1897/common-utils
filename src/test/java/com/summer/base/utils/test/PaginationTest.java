package com.summer.base.utils.test;

import com.alibaba.fastjson.JSON;
import com.base.pagination.PaginationQuery;
import com.base.pagination.PaginationResult;
import com.google.common.collect.Lists;
import com.summer.base.vo.AddressVo;
import com.summer.base.vo.UserVo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: hzduhao
 * Date: 2016/7/12
 * Time: 16:31
 */
public class PaginationTest {
    public static void main(String[] args) {
        List<UserVo> userVoList = Lists.newArrayList();

        AddressVo addressVo = new AddressVo();
        addressVo.setProvince("浙江省");
        addressVo.setCity("杭州市");
        addressVo.setRoad("浙大路");
        addressVo.setNumber(38);

        UserVo summer = new UserVo();
        summer.setName("summer");
        summer.setAge(28);
        summer.setEmail("summer@sina.com");
        summer.setPhone("15268538314");
        summer.setAddressVo(addressVo);

        UserVo solstice = new UserVo();
        solstice.setName("solstice");
        solstice.setAge(27);
        solstice.setEmail("solstice@sina.com");
        solstice.setPhone("15268538333");
        solstice.setAddressVo(addressVo);

        UserVo heaven = new UserVo();
        heaven.setName("heaven");
        heaven.setAge(30);
        heaven.setEmail("heaven@sina.com");
        heaven.setPhone("15268534313");
        heaven.setAddressVo(addressVo);

        userVoList.add(summer);
        userVoList.add(solstice);
        userVoList.add(heaven);

        PaginationQuery paginationQuery = new PaginationQuery(0,10,3);
        PaginationResult<UserVo> userVoPaginationResult = new PaginationResult<UserVo>(paginationQuery,userVoList);

//        JSON.writeJSONString
        System.out.println(JSON.toJSONString(userVoPaginationResult,true));
    }
}
