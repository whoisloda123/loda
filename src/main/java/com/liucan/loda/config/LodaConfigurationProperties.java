package com.liucan.loda.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Loda configuration properties
 *
 * @author liucan
 * @date 10/29/20 11:04 PM
 * 
 */
@Data
@PropertySource("classpath:loda.properties")
@Configuration
@ConfigurationProperties(prefix = "loda")
public class LodaConfigurationProperties {

    private String eventName;

    private String eventId;
}
