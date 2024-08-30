package com.conc.revise;

public class TaskCall {

    public void doTask() throws InterruptedException {        
        for(int i = 0; i < 10; i++) {
            Thread.sleep(1000);
        }
    }
    
}
