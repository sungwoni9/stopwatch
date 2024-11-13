package stopwatch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DisplayTimer extends Thread {

	private  int elapsedTime;
	private  int minute;
	private  int second;
	private  int hour;
	private boolean running;

	private Calendar cal = Calendar.getInstance();
	private TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
	private SimpleDateFormat sdf = new SimpleDateFormat("HH : mm : ss");

	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public DisplayTimer() {
		elapsedTime = 0;
		minute = 0;
		second = 0;
		hour = 0;
		running = true;
	}

	public void start() {
		run();
	}

	public void stopTimer() {
		running = false;
	}

	public DisplayTimer getInstance() {
		DisplayTimer instance = new DisplayTimer();
		return instance;

	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		DisplayTimer.second = second;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		DisplayTimer.minute = minute;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		DisplayTimer.hour = hour;
	}

	public void updateTime() {
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
