package com.liucan.loda.annotation.event;

import com.liucan.loda.annotation.LodaScan;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Loda event selector
 *
 * @author liucan
 * @date 10/8/20 2:22 PM
 * @see LodaScan
 */
public class LodaEventSelector implements ImportSelector {
    /**
     * {@link LodaScan#support} parameter
     */
    private static final String EVENT_DETERMINE_PARAMETER = "support";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(LodaScan.class.getName()));
        assert attributes != null;
        if (attributes.getBoolean(EVENT_DETERMINE_PARAMETER)) {
            return new String[]{LodaEventBeanDefinitionRegistrar.class.getName(),
                    CountLoda.class.getName(),
                    TownLoda.class.getName()};
        }
        return new String[0];
    }

    /**
     * Loda bean definition registry {@link LodaEventListenerBeanPostProcessor} and {@link LodaEventPublisher}
     *
     * @author liucan
     * @date 10/9/20 10:18 PM
     */
    public static class LodaEventBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

        private final BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

        /**
         * Register the {@link LodaEvent} of publisher that is {@link LodaEventPublisher}
         * and {@link LodaEventListenerBeanPostProcessor}
         * @param importingClassMetadata metadata of {@link LodaScan}
         * @param registry bean definition registry
         */
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
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
    }
}
