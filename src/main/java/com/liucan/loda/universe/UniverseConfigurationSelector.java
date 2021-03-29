package com.liucan.loda.universe;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Universe configuration import selector, whether import {@link HelloBeanDefinitionRegistryPostProcessor} or not
 * depends on {@link EnableUniverse#helloProxy()}
 * @author liucan
 * @see EnableUniverse
 * @see HelloBeanDefinitionRegistryPostProcessor
 */
public class UniverseConfigurationSelector implements ImportSelector {

    /**
     * 注解 {@link EnableUniverse} 的属性: {@value}
     */
    public static final String HELLO_PROXY_PARAMETER = "helloProxy";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(EnableUniverse.class.getName()));
        List<String> classNames = new ArrayList<>(Arrays.asList(
                HelloValueAnnotationBeanPostProcessor.class.getName(),
                TownFactoryBean.class.getName()));
        if (annotationAttributes.getBoolean(HELLO_PROXY_PARAMETER)) {
            classNames.add(HelloBeanDefinitionRegistryPostProcessor.class.getName());
        }
        return classNames.toArray(new String[0]);
    }
}
