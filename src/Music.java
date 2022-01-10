/* Sarina Li, Vivien Cai, Jiaan Li
* Sun January 09
* ICS4U1
* Music File
*/

// class used to play music when we end games, inherits the Thread class to allow for background activity, program doesn't
// stop executing and finish music before displaying next GUI screen
public class Music extends Thread {
    public void run() {
        // win music 
        if (GUI.AIwon && !GUI.AITIE) {
            MusicPlayer.playSound("ourlove.wav", true);
        // lose music (randomly picks between woman and superbass)
        } else if (!GUI.AIwon && !GUI.AITIE) {
            if (Math.random() > 0.5) {
                MusicPlayer.playSound("Woman.wav", true);
            } else {
                MusicPlayer.playSound("superbass.wav", true);
            }
        // tie music
        } else {
            MusicPlayer.playSound("superbass.wav", true);
        }

    }
}
