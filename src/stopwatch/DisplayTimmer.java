package stopwatch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DisplayTimmer implements Runnable {

	private static int elapsedTime;
	private static int minute;
	private static int second;
	private static int hour;

	private static Calendar cal = Calendar.getInstance();
	private static TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH : mm : ss");

	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public DisplayTimmer() {
		elapsedTime = 1;
		sdf.setTimeZone(tz);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);
		hour = cal.get(Calendar.HOUR);
	}

	public void start() {
		run();
	}

	public static DisplayTimmer getInstance() {
		DisplayTimmer instance = new DisplayTimmer();
		return instance;

	}

	public static int getSecond() {
		return second;
	}

	public static void setSecond(int second) {
		DisplayTimmer.second = second;
	}

	public static int getMinute() {
		return minute;
	}

	public static void setMinute(int minute) {
		DisplayTimmer.minute = minute;
	}

	public static int getHour() {
		return hour;
	}

	public static void setHour(int hour) {
		DisplayTimmer.hour = hour;
	}

	public static void updateTime() {
		second++;

		if (second > 59) {
			second = 0;
			minute++;
		}

		if (minute > 59) {
			minute = 0;
			hour++;
		}

		if (hour > 12) {
			hour = 0;
		}
	}

	public void run() {

		try {
			while (true) {
				updateTime();

				String currentTime = String.format("%02d : %02d : %02d [%d]", hour, minute, second, elapsedTime++);
				System.out.println(currentTime);

				writer.flush();

				Thread.sleep(1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();

			}

		}
	}

}
