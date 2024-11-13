package stopwatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Stopwatch extends Thread {

	private DisplayTimer displayTimer;
	private static Stopwatch instance;

	private boolean running;
	private boolean paused;

	private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static Stopwatch getInstance() {
		if (instance == null) {
			instance = new Stopwatch();
			instance.displayTimer = new DisplayTimer();
		}
		return instance;
	}

	@Override
	public void run() {
		running = true;
		paused = false;

		try {
			System.err.println("[q]STOP\n[h]HOLD\n[*]RERUN\n");

			displayTimer.start();

			while (running) {
				if (!paused) {

				}
				if (reader.ready()) {
					String input = reader.readLine();
					handleInput(input);
				}
				Thread.sleep(1000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(String input) {
		switch (input) {
		case "q":
			stopTimer();
			break;
		case "h":
			pauseTimer();
			break;
		case "*":
			resumeTimer();
			break;
		default:
			System.out.println();
			break;
		}
	}

	private void stopTimer() {
		running = false;
		displayTimer.stopTimer();
		try {
			writer.write("~~~~ Time Over ~~~~\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void pauseTimer() {
		paused = true;
		displayTimer.stopTimer();
		DisplayTimer.saveTime();
		try {
			writer.write("~~~~ 정지 ~~~~\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void resumeTimer() {
		paused = false;
		DisplayTimer.restoreTime();
		try {
			writer.write("~~~~ 재생 ~~~~\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
