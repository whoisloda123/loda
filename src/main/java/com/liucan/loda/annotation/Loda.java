package com.liucan.loda.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * The com.liucan.loda.loda component likes {@link Component}
 *
 * @author liucan
 * @see Component
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Loda {
    /**
     * Loda component name ,use default name if empty
     */
    String value() default "";
}
