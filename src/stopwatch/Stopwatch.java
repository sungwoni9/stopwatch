package stopwatch;

import java.util.Scanner;

public class Stopwatch extends Thread {

	private DisplayTimer displayTimer;
	private static Stopwatch instance;

	private boolean running;
	private boolean paused;
	private int startTime;
	private int elapsedTime;

	public static Stopwatch getInstance() {
		instance = new Stopwatch();
		return instance;
	}

	@Override
	public void run() {
		running = true;
		paused = false;
		displayTimer.start();
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
		System.out.println("Stopwatch stopped.");
	}

	private void pauseTimer() {
		paused = true;
		System.out.println("Stopwatch paused.");
	}

	private void resumeTimer() {
		if (paused) {
			paused = false;
			setStartTime((int) System.currentTimeMillis() - elapsedTime * 1000);
			System.out.println("Stopwatch resumed.");
		}
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

	public DisplayTimer getDisplayTimer() {
		return displayTimer;
	}

	public void setDisplayTimmer(DisplayTimer displayTimer) {
		this.displayTimer = displayTimer;
	}

}
