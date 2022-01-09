import java.util.TimerTask;

public class ScheduledTask extends TimerTask {

    static int minute = 0;
    static int second = 0;

    protected static String currentTime;

    // executes the code in this function every second
    public void run() {
        second++;
        if (second == 60) {
            minute++;
            second = 0;
        }
        // if ()
        if (second < 10) {
            currentTime = minute + ":0" + second;
            GUI.displayedTimer.setText("Timer: " + currentTime);
            // System.out.println(minute + ":0" + second);
        } else {
            currentTime = minute + ":" + second;
            GUI.displayedTimer.setText("Timer: " + currentTime);
            // System.out.println(minute + ":" + second);
        }
    }
}