package com.liucan.loda.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 可以用aop实现
 * 也可以用interceptor实现
 * 原则上都是拦截方法
 * @author liucan
 * @version 18-12-8
 */
@Component
@Aspect
@Slf4j
public class LoginAspect {

    @Pointcut("execution(* com.liucan.loda.mode.Country.*(..))")
    private void pointcutId() {
    }

    @Before("pointcutId()")
    public void beforeTask(JoinPoint jp) {
        log.info("[记录MyRestController请求]函数：{}，准备执行", jp.getSignature().toString());
    }

    @After("pointcutId()")
    public void afterTask(JoinPoint jp) {
        log.info("[记录MyRestController请求]函数：{}，开始执行", jp.getSignature().toString());
    }

    @AfterReturning(pointcut = "pointcutId()", returning = "retVal")
    public void afterRetruningTask(JoinPoint jp, Object retVal) {
        log.info("[记录MyRestController请求]函数：{}，执行完成，返回结果：{}", jp.getSignature().toString(), retVal);
    }

    @AfterThrowing(pointcut = "pointcutId()", throwing = "ex")
    public void afterThrowingTask(JoinPoint jp, Exception ex) {
        log.info("[记录MyRestController请求]函数：{}，执行抛出异常", jp.getSignature().toString(), ex);
    }

    @Around("pointcutId()")
    private Object aroundLogin(ProceedingJoinPoint jp) throws Throwable {
        try {
            return jp.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
