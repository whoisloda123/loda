package com.liucan.loda.annotation.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

/**
 * Loda bean definition registry {@link LodaEventListenerBeanPostProcessor} and {@link LodaEventPublisher}
 *
 * @author liucan
 * @date 10/9/20 10:18 PM
 */
public class LodaEventBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        AbstractBeanDefinition publisherBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(LodaEventPublisher.class)
                .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                .getRawBeanDefinition();
        String publisherBeanName = this.beanNameGenerator.generateBeanName(publisherBeanDefinition, registry);
        registry.registerBeanDefinition(publisherBeanName, publisherBeanDefinition);

        AbstractBeanDefinition listenerBeanPostProcessorBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(LodaEventListenerBeanPostProcessor.class)
                .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                .addConstructorArgReference(publisherBeanName)
                .getRawBeanDefinition();
        String listenerBeanPostProcessorBeanName = this.beanNameGenerator.generateBeanName(listenerBeanPostProcessorBeanDefinition, registry);
        registry.registerBeanDefinition(listenerBeanPostProcessorBeanName, listenerBeanPostProcessorBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
