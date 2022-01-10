/* Sarina Li, Vivien Cai, Jiaan Li
* Sun January 09
* ICS4U1
* ScheduledTask File
*/

/* IMPORTS 
* util.TimerTask: for using a java class that allows for execution periodically
*/

import java.util.TimerTask;

// Schedules a task in a certain interval. Executes the code inside of run mostly used to 
public class ScheduledTask extends TimerTask {

    static int minute = 0;
    static int second = 0;

    protected static String currentTime;

    // executes the code in this function every second
    public void run() {
        // increment the time each second
        second++;

        // if we reach a minute reset the second
        if (second == 60) {
            minute++;
            second = 0;
        }

        // for formatting (:00)
        if (second < 10) {
            currentTime = minute + ":0" + second;
            GUI.displayedTimer.setText("Timer: " + currentTime);
        } else {
            currentTime = minute + ":" + second;
            GUI.displayedTimer.setText("Timer: " + currentTime);
        }
    }
}