package com.liucan.loda.annotation.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link LodaEventMulticaster}.
 * @author liucan
 */
public class DefaultLodaEventMulticaster implements LodaEventMulticaster {

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Loda event listener container
     */
    private final List<LodaEventListener<?>> lodaEventListeners = new ArrayList<>();

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void invokeListener(LodaEventListener lodaEventListener, LodaEvent event) {
        try {
            lodaEventListener.onLodaEvent(event);
        } catch (Exception e) {
            logger.error("Handle Loda Event failed!" + e);
        }
    }

    @Override
    public void multicastEvent(LodaEvent event) {
        multicastEvent(event, null);
    }

    @Override
    public void multicastEvent(LodaEvent event, ResolvableType eventType) {
        ResolvableType type = eventType != null ? eventType : resolveDefaultEventType(event);
        retrieveEventListeners(type).forEach(lodaEventListener -> invokeListener(lodaEventListener, event));
    }

    /**
     * Determine whether this listener supports the given event type
     * @param lodaEventListener com.liucan.loda.loda event listener
     * @param eventType event type
     * @return whether supports
     */
    private boolean supportsType(LodaEventListener<?> lodaEventListener, ResolvableType eventType) {
        return new LodaEventListenerSupporter(lodaEventListener).supportsEventType(eventType);
    }

    /**
     * Retrieve all event listeners the given event type.
     * @param eventType the event type never {@code null}
     * @return event listeners collections
     */
    private Collection<LodaEventListener<?>> retrieveEventListeners(ResolvableType eventType) {
        List<LodaEventListener<?>> listeners = this.lodaEventListeners
                .stream()
                .filter(lodaEventListener -> supportsType(lodaEventListener, eventType))
                .collect(Collectors.toList());
        AnnotationAwareOrderComparator.sort(listeners);
        return listeners;
    }

    private ResolvableType resolveDefaultEventType(LodaEvent event) {
        return ResolvableType.forInstance(event);
    }

    @Override
    public void addLodaEventListener(LodaEventListener<?> listener) {
        this.lodaEventListeners.add(listener);
    }

    @Override
    public void removeLodaEventListener(LodaEventListener<?> listener) {
        this.lodaEventListeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        this.lodaEventListeners.clear();
    }
}
