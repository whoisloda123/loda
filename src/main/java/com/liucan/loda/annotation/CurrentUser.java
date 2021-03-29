package com.liucan.loda.annotation;

import java.lang.annotation.*;

/**
 * 获取当前登录用户信息 {@link User} 注解，配合 {@link User} 一起使用，注解在 {@code Controller} 类的
 * 方法参数上面用于获取当前用户信息，example：
 * <pre>{@code
 *     @GetMapping("test")
 *     public String test(@CurrentUser User user) {
 *         return "test";
 *     }
 * }
 * </pre>
 * @author liucan
 * @see CurrentUserMethodArgumentResolver
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
