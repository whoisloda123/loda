package com.liucan.loda.annotation.event;

import java.util.EventObject;

/**
 * Loda Event
 *
 * @author liucan
 * @see LodaEventListener
 */
public abstract class LodaEvent extends EventObject {

    public LodaEvent(Object source) {
        super(source);
    }
}
