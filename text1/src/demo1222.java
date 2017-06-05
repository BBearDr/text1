import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;



public class demo1222 {
	private static int count = 0;
	public static void main(String[] args) {
		class MyTimerTask extends TimerTask{
			
			public void run(){
				count = (count+1)%2;
				System.out.println("14---------");
				new Timer().schedule(new MyTimerTask(), 2000+2000*count);
			}
		}
		new Timer().schedule(new MyTimerTask(), 5000);
	}
	
}
