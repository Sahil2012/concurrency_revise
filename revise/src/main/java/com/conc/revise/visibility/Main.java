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
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            task.increment();
        });


        Future<?> f2 = service.submit(() -> {
            System.out.println(task.get());
        });

        f1.get();
        f2.get();

        System.out.println("Value : " + task.get());
        service.shutdown();
    }
    
}
