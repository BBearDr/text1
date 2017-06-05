
public class RunnableThread {
	
	public static void main(String[] args) {
		Thread1 runT = new Thread1();
		new Thread(null, runT,"D", 1).start();
		new Thread(null, runT,"E", 1).start();
		new Thread(null, runT,"F", 1).start();
	}
}
class Thread1 implements Runnable{
	private int count = 15;
	@Override
	public void run() {
		for(int i = 0;i<5;i++){
			System.out.println(Thread.currentThread().getName() + "ÔËÐÐ  count= " + count--);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
