package com.summer.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 16/11/22 下午1:52
 * @Description 通过Java反射机制访问实体对象工具
 */
public class ReflectUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ReflectUtils.class);
    private static final String PREFIX_GET = "get";
    private static final String PREFIX_SET = "set";

    /**
     * 反射调用实体对象空参数、空返回方法
     * @param domain
     * @param methodName
     * @param <Domain>
     */
    public static <Domain> void invoke(Domain domain,String methodName) {
        if (ObjectUtils.isNull(domain) || ObjectUtils.isNull(methodName)) {
            return;
        }

        try {
            Method method = domain.getClass().getMethod(methodName);
            method.invoke(domain);
        } catch (NoSuchMethodException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }

    /**
     * 发射调用实体对象带一个参数的方法，返回为空
     * @param domain
     * @param methodNmae
     * @param proClass
     * @param pro
     * @param <Domain>
     * @param <Pro>
     */
    public static <Domain,Pro> void invoke(Domain domain,String methodNmae,Class<Pro> proClass,Pro pro) {
        if (ObjectUtils.isNull(domain) || StringUtils.isEmpty(methodNmae) || ObjectUtils.isNull(proClass)) {
            return;
        }
        try {
            Method method = domain.getClass().getMethod(methodNmae, proClass);
            method.invoke(domain,pro);
        } catch (NoSuchMethodException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }

    /**
     * 反射调用实体对象带参数的方法，返回为空
     * @param domain
     * @param methodName
     * @param proClasses
     * @param pros
     * @param <Domain>
     */
    public static <Domain> void invoke(Domain domain,String methodName,Class<?>[] proClasses,Object...pros) {
        if (ObjectUtils.isNull(domain) || StringUtils.isEmpty(methodName)
            || ObjectUtils.isEmpty(proClasses) || ObjectUtils.isNull(pros)) {
            return;
        }
        if (proClasses.length != pros.length) {
            return;
        }

        try {
            domain.getClass().getMethod(methodName,proClasses).invoke(domain,pros);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            LOG.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }

    /**
     * 反射调用实体对象空参数方法
     * @param domain
     * @param methodName
     * @param valClass
     * @param <Domain>
     * @param <Val>
     * @return {@link Val}
     */
    public static <Domain,Val> Val invokeWithReturn(Domain domain,String methodName,Class<Val> valClass) {
        Val val = null;
        if (ObjectUtils.isNotNull(domain) && StringUtils.isNotEmpty(methodName) && ObjectUtils.isNotNull(valClass)) {
            try {
                val = valClass.cast(domain.getClass().getMethod(methodName).invoke(domain));
            } catch (IllegalAccessException e) {
                LOG.error(e.getMessage(),e);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                LOG.error(e.getMessage(),e);
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                LOG.error(e.getMessage(),e);
                e.printStackTrace();
            }
        }
        return val;
    }

    /**
     * 反射调用实体对象带一个参数和返回参数的方法
     * @param domain
     * @param methodName
     * @param proClass
     * @param valClass
     * @param pro
     * @param <Domain>
     * @param <Pro>
     * @param <Val>
     * @return {@link Val}
     */
    public static <Domain,Pro,Val> Val invokeWithReturn(Domain domain,String methodName,Class<Pro> proClass,Class<Val> valClass,Pro pro) {
        Val val = null;
        if (ObjectUtils.isNotNull(domain) && StringUtils.isNotEmpty(methodName)
            && ObjectUtils.isNotNull(pro) && ObjectUtils.isNotNull(valClass)) {
            try {
                val = valClass.cast(domain.getClass().getMethod(methodName, proClass).invoke(domain, pro));
            } catch (IllegalAccessException e) {
                LOG.error(e.getMessage(),e);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                LOG.error(e.getMessage(),e);
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                LOG.error(e.getMessage(),e);
                e.printStackTrace();
            }
        }
        return val;
    }

    /**
     * 反射调用实体对象带多个参数，且有返回值的方法
     * @param domain
     * @param methodName
     * @param proClasses
     * @param valClass
     * @param pros
     * @param <Domain>
     * @param <Val>
     * @return {@link Val}
     */
    public static <Domain,Val> Val invokeWithReturn(Domain domain,String methodName,Class<?>[] proClasses,Class<Val> valClass,Object...pros) {
        Val val = null;
        if (ObjectUtils.isNotNull(domain) && StringUtils.isNotEmpty(methodName)
            && ObjectUtils.isNotEmpty(proClasses) && ObjectUtils.isNotNull(valClass)) {
            try {
                val = valClass.cast(domain.getClass().getMethod(methodName, proClasses).invoke(domain, pros));
            } catch (Exception e) {
                LOG.error(e.getMessage(),e);
                e.printStackTrace();
            }
        }
        return val;
    }

    /**
     * 获取实体对象Domain指定属性字段的值
     * @param proClass Domain Class
     * @param propertyName 实体对象属性字段名
     * @param <Pro> 待返回属性字段数据类型
     * @return Pro
     */
    public static <Domain,Pro> Pro getFieldValueFromDomain(Domain domain,String propertyName,Class<Pro> proClass){
        if (ObjectUtils.isNull(domain) || StringUtils.isEmpty(propertyName) || ObjectUtils.isNull(proClass)) {
            return null;
        }

        return ReflectUtils.invokeWithReturn(domain,PREFIX_GET + StringUtils.firstAlphaToUpcase(propertyName),proClass);
    }

    /**
     * 与@{ReflectUtils#getFieldValueFromDomain}方法的功能相似，只不过这里对List集合进行操作
     * @param domains Domain Class
     * @param propertyName 实体对象属性字段名
     * @param proClass
     * @param <Domain>
     * @param <Pro> 待返回属性字段数据类型
     * @return List<Pro>
     */
    public static <Domain,Pro> List<Pro> getFiledValuesFromDomain(List<Domain> domains,String propertyName,Class<Pro> proClass){
        if (ObjectUtils.isEmpty(domains) || StringUtils.isEmpty(propertyName) || ObjectUtils.isNull(proClass)) {
            return null;
        }
        List<Pro> proList = new ArrayList<Pro>();
        for (Domain domain : domains) {
            proList.add(getFieldValueFromDomain(domain,propertyName,proClass));
        }
        return proList;
    }

    /**
     * 通过反射功能为实体类属性值设值
     * @param domain
     * @param propertyName
     * @param proClass
     * @param <Domain>
     * @param <Pro>
     */
    public static <Domain,Pro> void setAttribute(Domain domain,String propertyName,Class<Pro> proClass,Pro pro) {
        if (ObjectUtils.isNull(domain) || StringUtils.isEmpty(propertyName) || ObjectUtils.isNull(proClass)) {
            return;
        }

        ReflectUtils.invoke(domain,PREFIX_SET + StringUtils.firstAlphaToUpcase(propertyName),proClass,pro);
    }

    /**
     * 为多个实体对象设置同一个属性值
     * @param domains
     * @param propertyName
     * @param proClass
     * @param pro
     * @param <Domain>
     * @param <Pro>
     */
    public static <Domain,Pro> void setAttributes(List<Domain> domains,String propertyName,Class<Pro> proClass,Pro pro) {
        if (ObjectUtils.isEmpty(domains)) {
            return;
        }
        for (Domain domain : domains) {
            setAttribute(domain,propertyName,proClass,pro);
        }
    }

    /**
     * 为多个实体对象设置不同的属性值
     * @param domains
     * @param propertyNanmes
     * @param proClasses
     * @param pros
     * @param <Domain>
     * @param <Pro>
     */
    public static <Domain,Pro> void setAttributes(List<Domain> domains,List<String> propertyNanmes,List<Class<Pro>> proClasses,List<Pro> pros) {
        if (ObjectUtils.isEmpty(domains) || ObjectUtils.isEmpty(propertyNanmes) || ObjectUtils.isEmpty(proClasses)) {
            return;
        }
        int len = domains.size();
        if (len != propertyNanmes.size() || len != proClasses.size() || len != pros.size()) {
            return;
        }
        for (int i = 0; i < len; ++i) {
            setAttribute(domains.get(i),propertyNanmes.get(i),proClasses.get(i),pros.get(i));
        }
    }


    static class User {
        private String name;
        private String email;

        public User() {}

        public User(String name,String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void println() {
            System.out.println("println.....");
        }

        public String sayHello() {
            return "Hello,welcome to learn java reflect";
        }

        public String concat(String str1,String str2) {
            return str1 + str2;
        }
    }

    public static void main(String[] args) {
        /*List<User> users = Lists.newArrayList();
        users.add(new User());
        users.add(new User());
        users.add(new User());


        setAttributes(users,"name",String.class,"summer");
        setAttributes(users,"email",String.class,"summer@sina.com");

        for (User user : users) {
            System.out.println("name: " + user.getName());
            System.out.println("email: " + user.getEmail());
            System.out.println("---------------------------------");
        }*/
        User user = new User("summer","summer@sina.com");
//        Class<?>[] cls = new Class<?> [2];
       invoke(user,"println");
//        System.out.println(str);
    }

}
