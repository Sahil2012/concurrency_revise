package com.conc.revise.visibility;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        VolatileAtomic task = new VolatileAtomic(0);
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<?> f1 = service.submit(() -> {
           for(int i = 0; i < 1000; i++) {
            task.increment();
           }
        });


        Future<?> f2 = service.submit(() -> {
            for(int i = 0; i < 100; i++) {
                task.increment();
            }
        });

        f1.get();
        f2.get();

        //both f1 and f2 are writing on same resource hence Atomic/Sycnchonized
        System.out.println("Value : " + task.get());
        service.shutdown();
    }
    
}
