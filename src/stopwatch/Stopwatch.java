package stopwatch;

import java.util.Scanner;

public class Stopwatch extends Thread {

	public class getInstance extends Stopwatch {

	}

	private DisplayTimmer displayTimmer;
	private static Stopwatch instance;

	private boolean running;
	private boolean paused;
	private int startTime;
	private int elapsedTime;

	public static Stopwatch getInstance() {
		instance = new Stopwatch();
		return instance;
	}

	public void run() {
		running = true;
		setStartTime((int) System.currentTimeMillis());
		Scanner scanner = new Scanner(System.in);

		System.err.println("[q]STOP\n[h]HOLD\n[*]RERUN");

		while (running) {
			if (!paused) {
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		scanner.close();
	}

	private void handleInput(String input) {
	}

	private void pause() {

	}

	public void setInstance(Stopwatch instance) {
		this.instance = instance;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public DisplayTimmer getDisplayTimmer() {
		return displayTimmer;
	}

	public void setDisplayTimmer(DisplayTimmer displayTimmer) {
		this.displayTimmer = displayTimmer;
	}

}
