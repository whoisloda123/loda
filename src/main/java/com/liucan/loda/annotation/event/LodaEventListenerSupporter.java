package com.liucan.loda.annotation.event;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

/**
 * Supporter of {@link LodaEventListener} such as determines the listener support event
 *
 * @author liucan
 * @date 10/8/20 11:23 AM
 */
public class LodaEventListenerSupporter {

    private final LodaEventListener<LodaEvent> delegate;

    private final ResolvableType declaredEventType;

    public LodaEventListenerSupporter(LodaEventListener<?> delegate) {
        Assert.notNull(delegate, "Loda event listener not null!");
        this.delegate = (LodaEventListener<LodaEvent>) delegate;
        this.declaredEventType = resolveEventType(this.delegate);
    }

    /**
     * Determine whether this listener actually supports the given event type.
     *
     * @param type the event type(never {@code null})
     */
    public boolean supportsEventType(ResolvableType type) {
        return this.declaredEventType != null && this.declaredEventType.isAssignableFrom(type);
    }

    /**
     * resolve generic type form {@link LodaEventListener}
     *
     * @param lodaEventListener Loda event listener
     * @return generic type
     */
    private ResolvableType resolveEventType(LodaEventListener lodaEventListener) {
        ResolvableType resolvableType = resolveEventType(lodaEventListener.getClass());
        if (resolvableType == null || resolvableType.isAssignableFrom(LodaEvent.class)) {
            Class<?> targetClass = AopProxyUtils.ultimateTargetClass(lodaEventListener);
            if (targetClass != lodaEventListener.getClass()) {
                resolvableType = resolveEventType(targetClass);
            }
        }
        return resolvableType;
    }

    /**
     * resolve generic type form class which implements the {@link LodaEventListener}
     *
     * @param classType class implements the {@link LodaEventListener}
     * @return generic type
     */
    private ResolvableType resolveEventType(Class<?> classType) {
        ResolvableType resolvableType = ResolvableType.forClass(classType).as(LodaEventListener.class);
        return resolvableType.hasGenerics() ? resolvableType.getGeneric() : null;
    }
}
