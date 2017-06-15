package com.thread;

public class MulitThreadShareData {
	private int j ;
	public static void main(String[] args) {
		MulitThreadShareData thread = new MulitThreadShareData();
		IncThread inc = thread.new IncThread();
		DecThread dec = thread.new DecThread();
		for(int i =1 ;i<=2 ;i++){
			new Thread(inc).start();
			new Thread(dec).start();
		}
	}
	private synchronized void Inc(){
		j++;
		System.out.println(Thread.currentThread().getName()+"--inc"+j);
	}
	private synchronized void Dec(){
		j--;
		System.out.println(Thread.currentThread().getName()+"--Dec"+j);
	}
	class IncThread implements Runnable{
		@Override
		public void run() {
			for(int i = 1 ;i<=100 ;i++){
				Inc();
			}
		}
	}
	class DecThread implements Runnable{
		@Override
		public void run() {
			for(int i = 1 ;i<=100 ;i++){
				Dec();
			}
		}
	}
}
