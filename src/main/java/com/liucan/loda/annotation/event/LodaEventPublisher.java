package com.liucan.loda.annotation.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link LodaEvent} publisher
 *
 * @author liucan
 * @date 10/7/20 10:34 PM
 */
public class LodaEventPublisher {

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Loda event listener container
     */
    private Set<LodaEventListener> lodaEventListeners = new HashSet<>();

    private ResolvableType resolveDefaultEventType(LodaEvent event) {
        return ResolvableType.forInstance(event);
    }

    /**
     * Publish event
     *
     * @param event the com.liucan.loda.loda event never{@code null}
     */
    public void publishEvent(LodaEvent event) {
        publishEvent(event, null);
    }

    /**
     * Publish event
     *
     * @param event     the {@link LodaEvent}
     * @param eventType event type
     */
    public void publishEvent(LodaEvent event, @Nullable ResolvableType eventType) {
        ResolvableType type = eventType != null ? eventType : resolveDefaultEventType(event);
        retrieveEventListeners(type).forEach(lodaEventListener -> invokeListener(lodaEventListener, event));
    }

    private void invokeListener(LodaEventListener lodaEventListener, LodaEvent event) {
        try {
            lodaEventListener.onLodaEvent(event);
        } catch (Exception e) {
            logger.error("Handle Loda Event failed!" + e);
        }
    }

    /**
     * Determine whether this listener supports the given event type
     *
     * @param lodaEventListener com.liucan.loda.loda event listener
     * @param eventType         event type
     * @return whether supports
     */
    private boolean supportsType(LodaEventListener lodaEventListener, ResolvableType eventType) {
        return new LodaEventListenerSupporter(lodaEventListener).supportsEventType(eventType);
    }

    /**
     * retrieve all event listeners the given event type
     *
     * @param eventType the event type never {@code null}
     * @return event listeners collections
     */
    private Collection<LodaEventListener> retrieveEventListeners(ResolvableType eventType) {
        return this.lodaEventListeners
                .stream()
                .filter(lodaEventListener -> supportsType(lodaEventListener, eventType))
                .collect(Collectors.toSet());
    }

    public void addEventListener(LodaEventListener lodaEventListener) {
        this.lodaEventListeners.add(lodaEventListener);
    }

    public Set<LodaEventListener> getLodaEventListeners() {
        return this.lodaEventListeners;
    }

    public void setLodaEventListeners(Set<LodaEventListener> lodaEventListeners) {
        this.lodaEventListeners = lodaEventListeners;
    }

    public void removeListener(LodaEventListener lodaEventListener) {
        this.lodaEventListeners.remove(lodaEventListener);
    }
}
