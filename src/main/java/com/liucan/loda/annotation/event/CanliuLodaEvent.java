package com.liucan.loda.annotation.event;


import com.liucan.loda.mode.Town;

/**
 * Canliu event
 *
 * @author liucan
 * @date 10/7/20 10:18 PM
 */
public class CanliuLodaEvent extends LodaEvent {

    private Town town;

    public CanliuLodaEvent(Object source, Town town) {
        super(source);
        this.town = town;
    }

    public Town getTown() {
        return town;
    }
}
