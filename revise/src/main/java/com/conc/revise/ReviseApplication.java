package com.conc.revise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ReviseApplication {

	public static void main(String[] args) {

		TaskCall tc = new TaskCall();

		/**
		 * Executor is the parent class have only 1 method (void excute())
		 *
		 * ExecutorService is a child class of Executor have multiple methods submit,invokAll,awaitTerminate, isDone, etc. 
		 */
		ExecutorService service = Executors.newFixedThreadPool(2);

		List<Future<?>> li = new ArrayList<>();
		for(int i = 0; i < 10; i ++) {
			int fi = i;
			/**
			 *  3 submit method to start the thread execution(run)
			 * 	submit(runable) --> runnbale (run method) does not return anything --> hence Fututre<status>
			 * 	submit(callable) --> callable (call methid) do return generic <v> --> hance Furture<V>
			 * 	submit(runable,value) --> calls the runable(run) but returns Future<value>
			 */
			Future<?> f = service.submit(() -> {
			
				try {
					tc.doTask();
					System.out.println(fi);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			li.add(f);
		}

		service.shutdown();

		li.stream().forEach(f -> {
			try {
				// blocking the thread (join equivalent) return the value in future
				f.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		System.out.println("Done");
	}

}
