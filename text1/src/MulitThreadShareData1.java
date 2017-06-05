
public class MulitThreadShareData1 {
	
	public static void main(String[] args) {
		final ShareData data1 = new ShareData();
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i =1;i<=10;i++){
					data1.Inc();
				}
				
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i =1;i<=10;i++){
					data1.Dec();
				}
			}
		}).start();
	}
	
}
class ShareData{
	private int j=10;
	public synchronized void Inc(){
		j++;
		System.out.println(Thread.currentThread().getName()+"--inc"+j);
	}
	public synchronized void Dec(){
		j--;
		System.out.println(Thread.currentThread().getName()+"--Dec"+j);
	}
}
