package com.liucan.loda.mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 */
@Component
public class Universe {
    @Autowired
    private Village village;

    public Universe() {
        System.out.println("Instant universe");
    }
}
