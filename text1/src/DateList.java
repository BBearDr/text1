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
		//������ڼ���
		List<String> list = new ArrayList();
		try {
			Date date3 = df.parse(startTime);
			String time3 =  df.format(date3);
			Date date = df.parse(endTime);
			String time1 =  df.format(date);//��ֹʱ��
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date3);
//			String time2 = df.format(calendar.getTime());//�趨����ʼʱ��
			while(true){
				if(time1.equals(df.format(calendar.getTime()))){
					break;
				}else{
					list.add(df.format(calendar.getTime()));
					calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
				}
			}
			//�������һ��
			list.add(time1);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
