package com.liucan.loda.annotation.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * {@link LodaEventListener} registry
 * @author liucan
 */
public class LodaEventListenerBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    private LodaEventMulticaster lodaEventMulticaster;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LodaEventListener) {
            synchronized (this.lodaEventMulticaster) {
                this.lodaEventMulticaster.addLodaEventListener((LodaEventListener<?>) bean);
            }
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (this.lodaEventMulticaster != null) {
            synchronized (this.lodaEventMulticaster) {
                this.lodaEventMulticaster.removeLodaEventListener((LodaEventListener<?>) bean);
            }
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return bean instanceof LodaEventListener;
    }

    /**
     * https://blog.csdn.net/qq_27409289/article/details/100753656
     * 自动装配模式setAutowireMode，在通过beanDefiniton注入LodaEventListenerBeanPostProcessor的时候
     * 其中对象的属性有2种模式自动注入（构造函数和set注入:byType和byName）
     * 如果通过set方法的话，当set方法里面的对象如果在ioc里面有才会调用方法，不然不会调用
     * 即使没有定义属性，也会调用set方法
     * 默认为no，不通过构造函数和set方法注入
     */
    public void setLodaEventMulticaster(LodaEventMulticaster lodaEventMulticaster) {
        this.lodaEventMulticaster = lodaEventMulticaster;
    }
}
