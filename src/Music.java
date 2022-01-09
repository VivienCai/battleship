public class Music extends Thread {
    public void run() {
        if (GUI.AIwon && !GUI.AITIE) {
            MusicPlayer.playSound("ourlove.wav", true);
        } else if (!GUI.AIwon && !GUI.AITIE) {
            if (Math.random() > 0.5) {
                MusicPlayer.playSound("Woman.wav", true);
            } else {
                MusicPlayer.playSound("superbass.wav", true);
            }
        } else {
            MusicPlayer.playSound("superbass.wav", true);
        }

    }
}
