package com.liucan.loda.annotation.event;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

/**
 * Supporter of {@link LodaEventListener} determines the listener support loda event.
 * @author liucan
 */
public class LodaEventListenerSupporter {

    private final ResolvableType declaredEventType;

    public LodaEventListenerSupporter(LodaEventListener<?> delegate) {
        Assert.notNull(delegate, "Loda event listener not null!");
        this.declaredEventType = resolveEventType(delegate);
    }

    /**
     * Determine whether this listener actually supports the given event type.
     * @param type the event type(never {@code null})
     */
    public boolean supportsEventType(ResolvableType type) {
        return this.declaredEventType.isAssignableFrom(type);
    }

    /**
     * Resolve generic type form {@link LodaEventListener}
     * @param lodaEventListener Loda event listener
     * @return generic type
     */
    private ResolvableType resolveEventType(LodaEventListener<?> lodaEventListener) {
        ResolvableType resolvableType = resolveEventType(lodaEventListener.getClass());
        if (resolvableType.isAssignableFrom(LodaEvent.class)) {
            Class<?> targetClass = AopUtils.getTargetClass(lodaEventListener);
            if (targetClass != lodaEventListener.getClass()) {
                resolvableType = resolveEventType(targetClass);
            }
        }
        return resolvableType;
    }

    /**
     * Resolve event type form class which implements the {@link LodaEventListener}
     * @param classType class implements the {@link LodaEventListener}
     * @return generic type
     */
    private ResolvableType resolveEventType(Class<?> classType) {
        ResolvableType resolvableType = ResolvableType.forClass(classType).as(LodaEventListener.class);
        return resolvableType.hasGenerics() ? resolvableType.getGeneric() : ResolvableType.NONE;
    }
}
