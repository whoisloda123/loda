package com.liucan.loda.annotation.event;


import com.liucan.loda.mode.Country;

/**
 * Liucan com.liucan.loda.loda event
 *
 * @author liucan
 * @date 10/7/20 10:15 PM
 */
public class LiucanLodaEvent extends LodaEvent {

    private final Country country;

    public LiucanLodaEvent(Object source, Country country) {
        super(source);
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}
