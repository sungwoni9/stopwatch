package stopwatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DisplayTimmer implements Runnable {

	private int startTime;
	private int elapsedTime;

	private static Calendar cal = Calendar.getInstance();
	private TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH : mm : ss");

	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public DisplayTimmer() {
		startTime = (int) System.currentTimeMillis();
		elapsedTime = 1;
		sdf.setTimeZone(tz);
	}

	public static DisplayTimmer getInstance() {
		DisplayTimmer instance = new DisplayTimmer();
		return instance;

	}

	public void run() {

		try {
			while (true) {
				String currentTime = sdf.format(cal.getTime());
				cal.setTimeInMillis(System.currentTimeMillis());

				writer.write("\n" + currentTime + " [" + elapsedTime++ + "]");
				writer.flush();

				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e2) {

			}

		}
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
}
