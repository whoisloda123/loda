package com.liucan.loda.annotation.event;

import java.util.EventObject;

/**
 * Abstract class to be extended by all loda events.
 * @author liucan
 * @see LodaEventListener
 */
public abstract class LodaEvent extends EventObject {

    /**
     * The timestamp when the event occurred.
     */
    private final long timestamp;

    public LodaEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Return the system time in milliseconds when the event occurred.
     */
    public long getTimestamp() {
        return timestamp;
    }
}
