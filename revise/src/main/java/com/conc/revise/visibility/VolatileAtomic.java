package com.conc.revise.visibility;

public class VolatileAtomic {
    

    /**
     * Stored int the L1 cahche of the cpu core hence the update can happen for the thread running in that thred
     * as the value is updated into the L1 cache value is not updated for other threads
     * 
     */
    // private int x;

    /**
     * Stored in the L2 cache hence when the value gets updated by a thread in L1 cache it flushes that to L2 i.e. shared cahche
     * hence visible to all the other threads
     */
    private volatile int x;

    VolatileAtomic(int x) {
        this.x = x;
    }

    public void increment() {
        x ++;
    }

    public int get() {
        while(x == 0) {

        }
        return x;
    }

}
