package com.liucan.loda.proxy;

/**
 * @author liucan
 */
public class BmwCar implements Car {
    @Override
    public void run() {
        System.out.println("BMW car running!");
    }

    @Override
    public void stop() {
        System.out.println("BMW car stop!");
    }
}
