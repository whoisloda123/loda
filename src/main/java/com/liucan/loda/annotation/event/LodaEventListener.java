package com.liucan.loda.annotation.event;

import java.util.EventListener;

/**
 * {@link LodaEvent} listener,supports {@link org.springframework.core.annotation.Order} and
 * {@link org.springframework.core.Ordered} by invoked ordered.
 * @param <E> loda event
 * @author liucan
 * @see LodaEventMulticaster
 */
@FunctionalInterface
public interface LodaEventListener<E extends LodaEvent> extends EventListener {
    /**
     * Handler an loda event.
     * @param event loda event
     */
    void onLodaEvent(E event);
}
