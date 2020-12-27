package com.liucan.loda;

import com.liucan.loda.annotation.LodaScan;
import com.liucan.loda.universe.EnableUniverse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liucan
 * @version 2020/8/30
 */
@LodaScan
@EnableUniverse
@SpringBootApplication
public class LodaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(LodaApplication.class)
                .banner(new LodaApplicationBanner())
                .main(LodaApplication.class)
                .run(args);
    }
}
