package com.liucan.loda.mode;

import com.liucan.loda.annotation.LodaAutowired;
import com.liucan.loda.config.House;
import com.liucan.loda.universe.HelloValue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * @author liucan
 * @version 2020/8/30
 */
public class World<T extends Serializable> implements EnvironmentAware,
        ApplicationContextAware, ResourceLoaderAware, BeanNameAware, InitializingBean, DisposableBean {

    private ApplicationContext applicationContext;

    private Environment environment;

    private ResourceLoader resourceLoader;

    @HelloValue("中国")
    private String ContoryName;

    /**
     * name that indicates which user has used
     */
    private String userName;

    private String userId;

    private House house;

    @LodaAutowired
    private Country country;

    @Override
    public void setBeanName(String name) {
        System.out.println(String.format("setBeanName:%s", name));
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        country.test();
        System.out.println("afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void destroy1() {
        System.out.println("PreDestroy");
    }

    private List<String> list;
    private Map<String, Country> map;
    private T t;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
        this.applicationContext = applicationContext;
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                    System.out.println(actualTypeArgument.getTypeName());
                }
                Type ownerType = parameterizedType.getOwnerType();
                Type rawType = parameterizedType.getRawType();
            } else if (genericType instanceof TypeVariable) {
                TypeVariable<?> type = (TypeVariable<?>) genericType;
                Type[] bounds = type.getBounds();
                GenericDeclaration genericDeclaration = type.getGenericDeclaration();
                String typeName = type.getTypeName();
                String name = type.getName();
                System.out.println("fsf");
            }
        }
        Type genericSuperclass = this.getClass().getGenericSuperclass();

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        System.out.println("setEnvironment");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        System.out.println("setResourceLoader");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContoryName() {
        return ContoryName;
    }

    public void setContoryName(String contoryName) {
        ContoryName = contoryName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
