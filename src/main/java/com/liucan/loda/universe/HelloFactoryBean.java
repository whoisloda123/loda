package com.liucan.loda.universe;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;

/**
 * {@link IHello} factory bean
 *
 * @author liucan
 * @version 2020/8/30
 * @see HelloBeanDefinitionRegistryPostProcessor
 */
public class HelloFactoryBean implements FactoryBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * generator proxy object by cglib
     */
    @Override
    public IHello getObject() throws Exception {
        return (IHello) Enhancer.create(IHello.class, (MethodInterceptor) (o, method, objects, methodProxy) -> {
            if (method.getName().equals("getUserNames")) {
                return Arrays.asList("liucan", "can.liu");
            }
            return methodProxy.invokeSuper(o, objects);
        });
    }

    @Override
    public Class<?> getObjectType() {
        return IHello.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
