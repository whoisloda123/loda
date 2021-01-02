package com.liucan.loda.universe;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ReflectionUtils;

/**
 * Fill attributes with objects which has {@link HelloValue}.
 * @author liucan
 * @version 2020/8/30
 */
public class HelloValueAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, bean, field.getAnnotation(HelloValue.class).value());
        }, field -> field.isAnnotationPresent(HelloValue.class) && field.getType().isAssignableFrom(String.class));
        return null;
    }
}
