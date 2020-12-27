package com.liucan.loda.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * {@link LodaComponentScan} com.liucan.loda.loda component registrar
 *
 * @author liucan
 */
public class LodaBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware, ResourceLoaderAware {
    /**
     * The packages to be scan
     */
    private final Set<String> packagesToScan;

    private Environment environment;

    private ResourceLoader resourceLoader;

    public LodaBeanDefinitionRegistryPostProcessor(Set<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        LodaClassPathBeanDefinitionScanner beanDefinitionScanner = new LodaClassPathBeanDefinitionScanner(registry,
                this.environment,
                this.resourceLoader);
        beanDefinitionScanner.addIncludeFilter(new AnnotationTypeFilter(Loda.class));
        beanDefinitionScanner.scan(this.packagesToScan.toArray(new String[0]));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.addBeanPostProcessor(new LodaAutowiredAnnotationBeanPostProcessor(beanFactory));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
