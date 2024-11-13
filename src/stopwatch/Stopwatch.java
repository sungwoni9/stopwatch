package stopwatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Stopwatch extends Thread {

    private DisplayTimer displayTimer;

    private boolean running;
    private boolean paused;

    private static Stopwatch instance;
    private static StringBuilder buffer = new StringBuilder();
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Stopwatch stopwatch;

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
            displayTimer.start();

            buffer.append("[q]STOP\n[h]HOLD\n[*]RERUN\n");
            writer.append(buffer);
            buffer.setLength(0);
            writer.flush();

            while (running) {

                if (reader.ready()) {
                    String input = reader.readLine();
                    handleInput(input);
                }

                if (!paused) {
                    displayTimer.updateTime();
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
        buffer.append("~~~~ Time Over ~~~~\n");
        try {
            writer.append("소요시간 종료\n");
            buffer.setLength(0);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pauseTimer() {
        paused = true;
        buffer.append("~~~~ 정지 ~~~~\n");
        try {
            writer.append(buffer);
            buffer.setLength(0);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resumeTimer() {
        paused = false;
        buffer.append("~~~~ 재생 ~~~~\n");
        try {
            writer.append(buffer);
            buffer.setLength(0);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.getInstance();
        stopwatch.start();
    }
}