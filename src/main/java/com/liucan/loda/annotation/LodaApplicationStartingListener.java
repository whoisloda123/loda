package com.liucan.loda.annotation;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Loda application {@link ApplicationStartingEvent} listener
 *
 * @author liucan
 * @date 10/19/20 10:05 PM
 */
public class LodaApplicationStartingListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("app is starting");
    }
}
