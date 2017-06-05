package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ThreadPoolTest {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
//		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for(int i=1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable(){
				@Override
				public void run() {
					for(int j=1;j<=3;j++){
						System.out.println(Thread.currentThread().getName() +"--任务数："+ task +"第几次;"+j);
					}
				}
			});
			
		}
		Executors.newScheduledThreadPool(3).schedule(
				new Runnable(){
					@Override
					public void run() {
						System.out.println("boom");
					}}, 
					10,
					TimeUnit.SECONDS);
	}
}
