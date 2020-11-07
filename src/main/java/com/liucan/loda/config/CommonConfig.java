package com.liucan.loda.config;

import com.liucan.loda.mode.World;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Common configuration
 *
 * @author liucan
 * @version 2020/8/30
 */
@Configuration
@EnableConfigurationProperties
public class CommonConfig {
    @Bean
    public World<String> world() {
        World<String> world = new World();
        world.setUserName("fsfsfs");
        return world;
    }
}
