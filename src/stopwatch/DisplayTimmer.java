package stopwatch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DisplayTimmer implements Runnable {

	private Calendar cal = Calendar.getInstance();
	private TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
	private SimpleDateFormat sdf = new SimpleDateFormat("HH : mm : ss");
	
	public DisplayTimmer() {
		sdf.setTimeZone(tz);
	}
	
	public static DisplayTimmer getInstance() {
		DisplayTimmer instance = new DisplayTimmer();
		return instance;
	}
	
	public void run() {
		while (true) {
			try {
				cal.setTimeInMillis(System.currentTimeMillis());
				System.out.println("현재 시간: " + sdf.format(cal.getTime()));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
