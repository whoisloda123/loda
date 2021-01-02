package com.liucan.loda.universe;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.Arrays;

/**
 * {@link IHello} factory bean.
 * @author liucan
 * @version 2020/8/30
 * @see HelloBeanDefinitionRegistryPostProcessor
 */
public class HelloFactoryBean implements FactoryBean<IHello> {

    /**
     * Generator proxy object by cglib.
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
}
