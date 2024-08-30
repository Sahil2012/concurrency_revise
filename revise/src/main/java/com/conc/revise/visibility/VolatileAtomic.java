package com.conc.revise.visibility;

import java.util.concurrent.atomic.AtomicInteger;

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
     * 
     * Volatile ensure that the changes in this will be visible to other thread once they read it
     * Hence it is used for visbility of vars across threads
     */

    // private volatile int x;

    /**
     * Atomic classes gaurentees the visibility as well as the atomicity. We don't need to do synchronization explicitly
     * they are by default sync
     * 
     * It is same as using sync() block. It writes the value in L1 flushes it to L2 cache that too by atomic operations
     * Hence guarentees the visibilty across threads and also integrety.
     * 
     * But as we know it uses sync internally hence it is slow vs volatile 
     * (if we dont want to manipulate the resource in multiple threads only 1 will manipulate and other threads reads the value then we can use volatile)
     * (else we should use Atomic)
     */
    private AtomicInteger x;

    VolatileAtomic(int x) {
        this.x = new AtomicInteger(x);
    }

    public void increment() {
        x.incrementAndGet();
    }

    public int get() {
        return x.get();
    }

}
