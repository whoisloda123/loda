package com.liucan.loda.proxy;

import com.liucan.loda.mode.Village;
import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * The advisor of {@link com.liucan.loda.mode.Village}.
 * @author liucan
 */
public class VillagePointcutAdvisor extends DynamicMethodMatcherPointcut implements PointcutAdvisor {

    @Override
    public Pointcut getPointcut() {
        return this;
    }

    @Override
    public Advice getAdvice() {
        return (MethodBeforeAdvice) (method, args, target) -> System.out.println("开始执行了:" + method.getName());
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }

    /**
     * Determines whether target class is {@link Village}.
     */
    private boolean checkTargetClass(Class<?> targetClass) {
        return targetClass.isAssignableFrom(Village.class);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return checkTargetClass(targetClass);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        if (!checkTargetClass(targetClass)) {
            return false;
        }
        if (method.getName().equals("test")) {
            if (args[0] instanceof Boolean) {
                return (Boolean) args[0];
            }
        }
        return false;
    }
}
