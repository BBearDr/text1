import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateList {
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = "2016-10-10";
		String endTime = "2016-11-11";
		//存放日期集合
		List<String> list = new ArrayList();
		try {
			Date date3 = df.parse(startTime);
			String time3 =  df.format(date3);
			Date date = df.parse(endTime);
			String time1 =  df.format(date);//截止时间
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date3);
//			String time2 = df.format(calendar.getTime());//设定的起始时间
			while(true){
				if(time1.equals(df.format(calendar.getTime()))){
					break;
				}else{
					list.add(df.format(calendar.getTime()));
					calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
				}
			}
			//加上最后一天
			list.add(time1);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
