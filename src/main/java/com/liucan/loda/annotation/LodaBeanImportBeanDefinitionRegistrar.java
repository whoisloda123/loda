package com.liucan.loda.annotation;

import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Loda bean register as {@link LodaBeanDefinitionRegistryPostProcessor} and {@link LodaAutowiredAnnotationBeanPostProcessor}
 * @author liucan
 */
public class LodaBeanImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    private final BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registerLodaBeanDefinitionRegistryPostProcessor(importingClassMetadata, registry);
        //registerLodaAutowiredAnnotationBeanPostProcessor(importingClassMetadata, registry);
    }

    /**
     * register {@link LodaBeanDefinitionRegistryPostProcessor}
     */
    private void registerLodaBeanDefinitionRegistryPostProcessor(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AbstractBeanDefinition rawBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(LodaBeanDefinitionRegistryPostProcessor.class)
                .addConstructorArgValue(getPackagesToScan(importingClassMetadata))
                .getRawBeanDefinition();
        String beanName = this.beanNameGenerator.generateBeanName(rawBeanDefinition, registry);
        registry.registerBeanDefinition(beanName, rawBeanDefinition);
    }

    /**
     * register {@link LodaAutowiredAnnotationBeanPostProcessor}
     */
    private void registerLodaAutowiredAnnotationBeanPostProcessor(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AbstractBeanDefinition rawBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(LodaAutowiredAnnotationBeanPostProcessor.class)
                .getRawBeanDefinition();
        String beanName = this.beanNameGenerator.generateBeanName(rawBeanDefinition, registry);
        registry.registerBeanDefinition(beanName, rawBeanDefinition);
    }

    /**
     * Get the packages from {@link LodaComponentScan}
     * @param metadata metadata
     * @return The packages to be scan
     */
    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(LodaComponentScan.class.getName()));
        Class<?>[] basePackageClasses = annotationAttributes.getClassArray("basePackageClasses");
        Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(annotationAttributes.getStringArray("basePackages")));
        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }
        if (packagesToScan.isEmpty()) {
            packagesToScan.add(ClassUtils.getPackageName(metadata.getClassName()));
        }
        return packagesToScan;
    }
}
