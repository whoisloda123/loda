package com.liucan.loda;

import com.liucan.loda.annotation.LodaScan;
import com.liucan.loda.universe.EnableUniverse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.util.StopWatch;

/**
 * @author liucan
 * @version 2020/8/30
 */
@LodaScan
@EnableUniverse
@SpringBootApplication
public class LodaApplication {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("统计loda应用程序时间");
        stopWatch.start("启动时间");
        ConfigurableApplicationContext context = new SpringApplicationBuilder(LodaApplication.class)
                .banner(new LodaApplicationBanner())
                .main(LodaApplication.class)
                .run(args);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
