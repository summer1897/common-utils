package com.summer.base.utils.test;

import com.google.common.collect.Lists;
import com.summer.base.utils.PropertyExtractUtils;
import com.summer.base.vo.UserVo;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by summer on 2016/7/9.
 */
public class PropertyExtractUtilsTest {

    static void test1(){
        UserVo userVo = new UserVo();
        userVo.setName("summer");
        userVo.setAge(28);
        userVo.setPhone("15268528314");
        userVo.setEmail("summer@sina.com");
        String name = PropertyExtractUtils.extractPropertyFromDomain(userVo,"name",String.class);
        System.out.println("name: "+name);
    }

    static void test2(){
        List<UserVo> userVoList = Lists.newArrayList();
        UserVo userVo = new UserVo();
        userVo.setName("summer");
        userVo.setAge(28);
        userVo.setPhone("15268528314");
        userVo.setEmail("summer@sina.com");

        UserVo solstice = new UserVo();
        solstice.setName("solstice");
        solstice.setAge(27);
        solstice.setPhone("15268528316");
        solstice.setEmail("solstice@sina.com");

        UserVo sky = new UserVo();
        sky.setName("sky");
        sky.setAge(30);
        sky.setPhone("18883308404");
        sky.setEmail("sky@sina.com");

        userVoList.add(userVo);
        userVoList.add(solstice);
        userVoList.add(sky);

        List<String> names = PropertyExtractUtils.extractPropertyFromDomain(userVoList,"name",String.class);

        for(String name: names){
            System.out.println("name: "+name);
        }
    }

    static void test3(){
        UserVo userVo = new UserVo();
        userVo.setName("summer");
        userVo.setAge(28);
        userVo.setPhone("15268528314");
        userVo.setEmail("summer@sina.com");
        Map<String,UserVo> userVoMap = PropertyExtractUtils.extractPropertyFromDomainToMap(userVo,"name",String.class);
        for (String key : userVoMap.keySet()){
            System.out.println("key: "+key);
            System.out.println(userVoMap.get(key));
        }
    }

    static void test4(){
        List<UserVo> userVoList = Lists.newArrayList();
        UserVo userVo = new UserVo();
        userVo.setName("summer");
        userVo.setAge(28);
        userVo.setPhone("15268528314");
        userVo.setEmail("summer@sina.com");

        UserVo solstice = new UserVo();
        solstice.setName("solstice");
        solstice.setAge(27);
        solstice.setPhone("15268528316");
        solstice.setEmail("solstice@sina.com");

        UserVo sky = new UserVo();
        sky.setName("sky");
        sky.setAge(30);
        sky.setPhone("18883308404");
        sky.setEmail("sky@sina.com");

        userVoList.add(userVo);
        userVoList.add(solstice);
        userVoList.add(sky);

        Map<String,UserVo> maps = PropertyExtractUtils.extractPropertyFromDomainToMap(userVoList,"name",String.class);

        for(String key : maps.keySet()){
            System.out.println("key: "+key);
            System.out.println(maps.get(key));
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

    @Test
    public void extractPropertyFromDomainToMapListTest(){
        List<UserVo> userVoList = Lists.newArrayList();
        UserVo userVo = new UserVo();
        userVo.setName("summer");
        userVo.setAge(28);
        userVo.setPhone("15268528314");
        userVo.setEmail("summer@sina.com");

        UserVo solstice = new UserVo();
        solstice.setName("solstice");
        solstice.setAge(28);
        solstice.setPhone("15268528316");
        solstice.setEmail("solstice@sina.com");

        UserVo sky = new UserVo();
        sky.setName("sky");
        sky.setAge(30);
        sky.setPhone("18883308404");
        sky.setEmail("sky@sina.com");

        userVoList.add(userVo);
        userVoList.add(solstice);
        userVoList.add(sky);

        Map<Integer,List<UserVo>> userVoMaps = PropertyExtractUtils.extractPropertyFromDomainToMapList(userVoList,"age",Integer.class);

        for(Integer age : userVoMaps.keySet()){
            System.out.println(userVoMaps.get(age));
        }
    }

    public static void main(String[] args) {
        test4();
    }
}
