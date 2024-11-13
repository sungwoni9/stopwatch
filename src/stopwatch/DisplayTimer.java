package stopwatch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DisplayTimer implements Runnable {

	private static int elapsedTime;
	private static int minute;
	private static int second;
	private static int hour;

	private boolean paused;

	private static Thread timerThread;

	private static int saveSecond;
	private static int saveMinute;
	private static int saveHour;

	private Calendar cal = Calendar.getInstance();

	private static StringBuilder buffer = new StringBuilder();
	private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public DisplayTimer() {
		hour = cal.get(Calendar.HOUR_OF_DAY);
		;
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);
		elapsedTime = 1;
		paused = true;
	}

	public void start() {
		if (timerThread == null) {
			timerThread = new Thread(this);
			timerThread.start();
		}
	}

	public void stopTimer() {
		if (timerThread != null) {
			try {
				timerThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getSecond() {
		return second;
	}

	public static void setSecond(int second) {
		DisplayTimer.second = second;
	}

	public static int getMinute() {
		return minute;
	}

	public static void setMinute(int minute) {
		DisplayTimer.minute = minute;
	}

	public static int getHour() {
		return hour;
	}

	public static void setHour(int hour) {
		DisplayTimer.hour = hour;
	}

	public static int getelapsedTime() {
		return elapsedTime;
	}

	public static void saveTime() {
		saveSecond = second;
		saveMinute = minute;
		saveHour = hour;
	}

	public static void restoreTime() {
		second = saveSecond;
		minute = saveMinute;
		hour = saveHour;
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

	@Override
	public void run() {
		try {
			while (true) {
				if (!paused) {
					DisplayTimer.updateTime();
				}

				buffer.setLength(0);
				buffer.append(String.format("%02d : %02d : %02d [%d]", hour, minute, second, elapsedTime));
				writer.write(buffer.toString());
				writer.newLine();
				writer.flush();

				Thread.sleep(1000);
				elapsedTime++;
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
