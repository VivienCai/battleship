import java.util.TimerTask;

public class ScheduledTask extends TimerTask {

    int minute = 0; 
    int second = 0;

    // executes the code in this function every second
	public void run() {
        second++;
        if (second == 60) {
            minute++;
            second = 0;
        }
        if (second < 10) {
            System.out.println(minute + ":0" + second);
        } else {
            System.out.println(minute + ":" + second);
        }
	}
}