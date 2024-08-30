package com.conc.revise.poolthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTypesR {

    public static void main(String[] args) {


        /**
         * Creates a Pool of thread of nThreads with min core size and max size is same
         * Saves the time of crreating the threads
         * 
         * Deciding the no of threads
         * --> If IO operation --> less work for CPU --> Thread number can be high because more threads will be waiting
         * --> If heavy computation --> Thread number == CPU Cores
         */
        ExecutorService fixed = Executors.newFixedThreadPool(10);

        /**
         * submit(Callable) : Future<Return value of Call funtion>
         * 
         * submit(Runnable) : Future<No Value but status>
         * submit(Runnable,value) : Future<value>
         */
        fixed.submit(() -> {
            System.out.println("Fixed thread pool executed me");
        });

        fixed.shutdown();

        /**
         * Caretes a single thraed if we have multiple task these go into a queue and gets executed sequntially
         */
        ExecutorService single = Executors.newSingleThreadExecutor();

        single.submit(() -> {
            System.out.println("Single Thread pool executed me");
        });

        single.shutdown();
        
        /**
         * Craetes a pool of 0 threads initials and then increase or decrease the thread numbers (core : 0, max : Integer.MAX_VALUE
         * 
         * When a task is sumitted it checks into the pool 
         * --> if have any idel thread assign the task
         * --> else create 1 thread and then assign to it
         * 
         * --> it also monitor for the threads if they are inactive for more than 60 secs(kill time) then destroy that thread
         */
        ExecutorService cache = Executors.newCachedThreadPool();

        cache.submit(() -> {
            System.out.println("Cached thread pool executed me");
        });

        cache.shutdown();


        /**
         * Creates thread pool of given number
         * 
         * ScheduleExecutorService is typically used when we want to to a task after some scheduled time
         */
        ScheduledExecutorService scheduleTP = Executors.newScheduledThreadPool(5);

        /**
         * schedule(Callable/Runnable,time,unit) : SheduledFututre<?>
         * 
         * Execute the task after delay time
         */
        scheduleTP.schedule(() -> {
            System.out.println("schedule thread pool will execute me after some time");

        }, 5, TimeUnit.SECONDS);

        /**
         * scheduleAfterFixedRate(Callable,Runnable,time,period,unit) : ScheduledFuture<?>
         * 
         * Excecute the 1st task after the initial wait 
         * and then keep on repeatedly executing it after every 10 secs of (period time)
         */
        scheduleTP.scheduleAtFixedRate(() -> {
            System.out.println("schedule thread pool will execute me after some time");

        }, 5,10, TimeUnit.SECONDS);

        /**
         * scheduleAfterFixedDelay(Callable,Runnable,time,period,unit) : ScheduledFuture<?>
         * 
         * Excecute the 1st task after the initial wait 
         * and then keep on repeatedly executing it after every 10 secs of (period time) from the last task completion time unless shutdown
         */
        scheduleTP.scheduleWithFixedDelay(() -> {
            System.out.println("schedule thread pool will execute me after some time");
        }, 10, 10, TimeUnit.SECONDS);
        
        scheduleTP.shutdown();
    }
}
