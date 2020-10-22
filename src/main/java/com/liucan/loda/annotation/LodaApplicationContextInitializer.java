package com.liucan.loda.annotation;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

/**
 * Loda application context init before {@link ConfigurableApplicationContext#refresh()}
 * you can set properties or add profile by {@link ConfigurableApplicationContext#getEnvironment()}
 *
 * @author liucan
 * @date 10/20/20 10:15 PM
 */
public class LodaApplicationContextInitializer implements ApplicationContextInitializer, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println(activeProfiles);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
