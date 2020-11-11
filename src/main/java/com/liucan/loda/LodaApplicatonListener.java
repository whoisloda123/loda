package com.liucan.loda;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;

/**
 * Loda application {@link ApplicationStartedEvent} and {@link ApplicationStartingEvent}listener.
 *
 * @author liucan
 * @date 11/11/20 10:20 PM
 */
public class LodaApplicatonListener implements GenericApplicationListener {
    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return ResolvableType.forClass(ApplicationStartedEvent.class).isAssignableFrom(eventType) ||
                ResolvableType.forClass(ApplicationStartingEvent.class).isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            onApplicationStartingEvent((ApplicationStartingEvent) event);
        } else if (event instanceof ApplicationStartedEvent) {
            onApplicationStartedEvent((ApplicationStartedEvent) event);
        }
    }

    private void onApplicationStartingEvent(ApplicationStartingEvent event) {
        System.out.println("app is starting");
    }

    private void onApplicationStartedEvent(ApplicationStartedEvent event) {
        System.out.println("app is started");
    }
}
