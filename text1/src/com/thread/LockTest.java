package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static void main(String[] args) {
		final Output output = new Output();
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		threadPool.execute(new Runnable(){
			@Override
			public void run() {
				
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					output.output("zhangsan");
				}
//				System.out.println(Thread.currentThread().getName() + "线程1");
			}
		});
		threadPool.execute(new Runnable(){
			@Override
			public void run() {
				
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					output.output("lisi");
				}
//				System.out.println(Thread.currentThread().getName() + "线程2");
			}
		});
	}
	static class Output{
		Lock lock = new ReentrantLock();
		public void output(String name){
			lock.lock();
			try {
				for(int i=0;i<name.length();i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();
			}
		}
		public synchronized void output2(String name){
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		public static void output3(String name){
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}
