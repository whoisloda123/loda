package com.liucan.loda.mode;

import com.liucan.loda.proxy.VillagePointcutAdvisor;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 */
@Component
@Import(VillagePointcutAdvisor.class)
public class Village {
    public Village() {
        System.out.println("Instant village");
    }

    public void test(Boolean test) {
        System.out.println("param test is" + test);
    }
}
