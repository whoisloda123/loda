package com.liucan.loda.annotation;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Set;

/**
 * Loda {@link ClassPathBeanDefinitionScanner} that expose some methods to be public.
 * the {@link Loda} scanner
 *
 * @author liucan
 */
public class LodaClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public LodaClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry,
                                              Environment environment,
                                              ResourceLoader resourceLoader) {
        super(registry, false, environment, resourceLoader);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }
}
