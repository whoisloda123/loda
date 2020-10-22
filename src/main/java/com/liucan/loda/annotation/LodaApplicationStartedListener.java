package com.liucan.loda.annotation;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Loda application {@link ApplicationStartedEvent} listener
 *
 * @author liucan
 * @date 10/19/20 10:07 PM
 */
public class LodaApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("app is started");
    }
}
