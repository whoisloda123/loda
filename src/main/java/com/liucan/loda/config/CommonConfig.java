package com.liucan.loda.config;

import com.liucan.loda.annotation.User;
import com.liucan.loda.mode.Universe;
import com.liucan.loda.mode.World;
import org.springframework.beans.factory.ObjectProvider;
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

    private final Universe universe;

    public final User user;

    private final ObjectProvider<House> houseProvider;

    public CommonConfig(Universe universe, ObjectProvider<User> userObjectProvider, ObjectProvider<House> houseProvider) {
        this.universe = universe;
        this.user = userObjectProvider.getIfAvailable();
        this.houseProvider = houseProvider;
    }

    @Bean
    public World<String> world() {
        World<String> world = new World();
        world.setUserName("fsfsfs");
        this.houseProvider.stream().findAny().ifPresent(world::setHouse);
        return world;
    }
}
