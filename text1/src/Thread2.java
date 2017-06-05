import java.util.Arrays;


public class Thread2 implements Runnable {
	private String name;
	private String[] prev;
	private String[] self;
	
	public Thread2(String name, String[] prev, String[] self) {
		super();
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count  = 5;
		while(count > 0){
			System.out.println("µÚ"+count+"´Î");
			synchronized (prev) {//c,a,b
				synchronized (self) {//a,b,c
					System.out.println(name);
					count--;
					self.notify();
					System.out.println("26--"+Arrays.toString(self));
				}
				try {
					System.out.println("29---"+Arrays.toString(prev));
					prev.wait();
					System.out.println("30---"+Arrays.toString(prev));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		String[] a = {"a"};
		String[] b = {"b"};
		String[] c = {"c"};
		Thread2 pa = new Thread2("A", c, a);
		Thread2 pb = new Thread2("B", a, b);
		Thread2 pc = new Thread2("C", b, c);
		new Thread(pa).start();
		Thread.sleep(2000);
		new Thread(pb).start();
		Thread.sleep(2000);
		new Thread(pc).start();
		Thread.sleep(2000);
		System.out.println("48");
	}
	
}
