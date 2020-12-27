package com.liucan.loda.annotation.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * {@link LodaEventListener} registry
 *
 * @author liucan
 * @date 10/7/20 10:20 PM
 */
public class LodaEventListenerBeanPostProcessor implements BeanPostProcessor, DestructionAwareBeanPostProcessor {

    private final LodaEventMulticaster lodaEventMulticaster;

    public LodaEventListenerBeanPostProcessor(LodaEventMulticaster lodaEventMulticaster) {
        this.lodaEventMulticaster = lodaEventMulticaster;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LodaEventListener) {
            this.lodaEventMulticaster.addEventListener((LodaEventListener) bean);
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (bean instanceof LodaEventListener) {
            this.lodaEventMulticaster.removeListener((LodaEventListener) bean);
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return bean instanceof LodaEventListener;
    }
}
