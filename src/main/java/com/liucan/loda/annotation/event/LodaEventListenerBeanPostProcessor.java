package com.liucan.loda.annotation.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * {@link LodaEventListener} registry
 * @author liucan
 */
public class LodaEventListenerBeanPostProcessor implements DestructionAwareBeanPostProcessor {

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
            synchronized (this.lodaEventMulticaster) {
                this.lodaEventMulticaster.addLodaEventListener((LodaEventListener<?>) bean);
            }
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        synchronized (this.lodaEventMulticaster) {
            this.lodaEventMulticaster.removeLodaEventListener((LodaEventListener<?>) bean);
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return bean instanceof LodaEventListener;
    }
}
