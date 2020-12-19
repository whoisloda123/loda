package com.liucan.loda.universe;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

/**
 * generate {@link IHello} implements and register to spring bean in dynamic
 *
 * @author liucan
 * @version 2020/8/30
 * @see HelloFactoryBean
 */
public class HelloBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private final BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(HelloFactoryBean.class)
                .setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE)
                .getRawBeanDefinition();
        String beanName = this.beanNameGenerator.generateBeanName(beanDefinition, registry);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}
