package com.conc.revise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
public class ReviseApplication {

	public static void main(String[] args) {
		// SpringApplication.run(ReviseApplication.class, args);

		TaskCall tc = new TaskCall();

		ExecutorService service = Executors.newFixedThreadPool(2);

		List<Future<?>> li = new ArrayList<>();
		for(int i = 0; i < 10; i ++) {
			int fi = i;
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
