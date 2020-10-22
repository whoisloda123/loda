package com.liucan.loda;

import com.liucan.loda.annotation.LodaScan;
import com.liucan.loda.annotation.event.LiucanLodaEvent;
import com.liucan.loda.annotation.event.LodaEventPublisher;
import com.liucan.loda.mode.Country;
import com.liucan.loda.mode.Town;
import com.liucan.loda.mode.World;
import com.liucan.loda.universe.EnableUniverse;
import com.liucan.loda.universe.IHello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * @author liucan
 * @version 2020/8/30
 */
@LodaScan
@EnableUniverse
@SpringBootApplication
public class LodaApplication {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Hell application has been shut down!");
        }));

        ConfigurableApplicationContext context = SpringApplication.run(LodaApplication.class, args);
        System.out.println(context.getBean(World.class).toString());
        int beanDefinitionCount = context.getBeanFactory().getBeanDefinitionCount();
        Country bean1 = context.getBean(Country.class);
        context.getBean(Country.class).test();

        //Object bean3 = context.getBean("townFactoryBean");
        Town bean2 = context.getBean(Town.class);

        IHello bean = context.getBean(IHello.class);
        if (bean != null) {
            List<String> userNames = bean.getUserNames();
            System.out.println(userNames);
        }

        LodaEventPublisher lodaEventPublisher = context.getBean(LodaEventPublisher.class);
        lodaEventPublisher.publishEvent(new LiucanLodaEvent(context, bean1));
    }
}
