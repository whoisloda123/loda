package com.liucan.loda.annotation.event;

import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * Interface to be implemented by objects that can manage a number of
 * {@link LodaEventListener} objects, and publish events to them.
 * @author liucan
 */
public interface LodaEventMulticaster {
    /**
     * Multicast the given loda event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(LodaEvent event);

    /**
     * Multicast the given loda event to appropriate listeners.
     * @param event the event to multicast
     * @param eventType the type of event (can be null)
     */
    void multicastEvent(LodaEvent event, @Nullable ResolvableType eventType);

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addLodaEventListener(LodaEventListener<?> listener);

    /**
     * Remove a listener from notification list.
     * @param listener the listener to remove
     */
    void removeLodaEventListener(LodaEventListener<?> listener);

    /**
     * Remove all listeners registered with this multicaster.
     */
    void removeAllListeners();
}
