package com.liucan.loda.proxy;

import com.liucan.loda.mode.Village;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author liucan
 */
public class ProxyTest {

    public static void test(ApplicationContext context) {
        Car car = new BmwCar();
        Car proxyCar = (Car) Proxy.newProxyInstance(car.getClass().getClassLoader(),
                car.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    if ("run".equals(method.getName())) {
                        System.out.println("Call car method start");
                        method.invoke(car, args);
                        System.out.println("Call car method end");
                    }
                    return null;
                });
        proxyCar.run();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BmwCar.class);
        enhancer.setCallback((MethodInterceptor) (o, method, args, methodProxy) -> {
            System.out.println("Call car method start111");
            return methodProxy.invokeSuper(o, args);
        });
        BmwCar cglibCar = (BmwCar) enhancer.create();
        cglibCar.run();

        Village bean = context.getBean(Village.class);
        bean.test(false);
    }
}
