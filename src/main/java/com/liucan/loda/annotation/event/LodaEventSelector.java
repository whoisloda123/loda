package com.liucan.loda.annotation.event;

import com.liucan.loda.annotation.LodaScan;
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
        if (attributes.getBoolean(EVENT_DETERMINE_PARAMETER)) {
            return new String[]{LodaEventBeanDefinitionRegistryPostProcessor.class.getName(),
                    CountLoda.class.getName(),
                    TownLoda.class.getName()};
        }
        return new String[0];
    }
}
