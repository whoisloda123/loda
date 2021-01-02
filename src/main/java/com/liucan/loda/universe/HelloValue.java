package com.liucan.loda.universe;

import java.lang.annotation.*;

/**
 * 自动设置属性值
 * @author liucan
 * @version 2020/8/30
 * @see HelloValueAnnotationBeanPostProcessor
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HelloValue {
    /**
     * Set the hello value.
     * @return value
     */
    String value() default "";
}
