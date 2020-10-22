package com.liucan.loda.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * scan {@link Loda} components to spring ioc container
 * {@link LodaBeanImportBeanDefinitionRegistrar}
 *
 * @author liucan
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(LodaBeanImportBeanDefinitionRegistrar.class)
public @interface LodaComponentScan {
    /**
     * Alias for {@link #basePackages()} attribute ,Allows for more concise annotation
     * declarations e.g.: {@code @LodaScan("org.my.pkg")} instead of
     * {@code @LodaScan(basePackages="org.my.pkg")}.
     *
     * @return base packages to scan
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * Base packages to scan for annotated com.liucan.loda.loda {@link #value()} is an
     * alias for for the attribute
     *
     * @return base packages to scan
     */
    @AliasFor("value")
    String[] basePackages() default {};

    /**
     * Type-safe alternative to {@link #basePackages()} for specifying the packages to
     * scan for annotated servlet components. The package of each class specified will be
     * scanned.
     *
     * @return classes from the base packages to scan
     */
    Class<?>[] basePackageClasses() default {};

}
