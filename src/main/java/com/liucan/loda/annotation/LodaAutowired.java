package com.liucan.loda.annotation;

import java.lang.annotation.*;

/**
 * Auto inject the {@link Loda} object likes {@link org.springframework.beans.factory.annotation.Autowired}
 *
 * @author liucan
 * @see org.springframework.beans.factory.annotation.Autowired
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LodaAutowired {
    /**
     * Declares whither the annotated dependency required
     * default {@code ture}
     */
    boolean required() default true;
}
