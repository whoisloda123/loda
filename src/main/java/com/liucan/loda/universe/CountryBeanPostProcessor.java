package com.liucan.loda.universe;

import com.liucan.loda.mode.Country;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.beans.PropertyDescriptor;

/**
 * https://blog.csdn.net/qq_38526573/article/details/88091702
 *
 * @author liucan
 * @version 2020/8/30
 */
public class CountryBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    /**
     * bean初始化之前
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass == Country.class) {
            System.out.println("开始初始化：" + beanName);
            //利用cglib动态生成代理类
            return Enhancer.create(Country.class, (MethodInterceptor) (o, method, objects, methodProxy) -> {
                System.out.println("目标方法执行前:" + method + "\n");
                Object object = methodProxy.invokeSuper(o, objects);
                System.out.println("目标方法执行后:" + method + "\n");
                return object;
            });
        }
        return null;
    }

    /**
     * bean初始化之后
     *
     * @return 如果为true可能会调用postProcessPropertyValues
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    /**
     * 设置bean属性
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return pvs;
    }
}
