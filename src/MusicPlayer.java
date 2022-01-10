/* Sarina Li, Vivien Cai, Jiaan Li
* Sun January 09
* ICS4U1
* MusicPlayer File
*/

/* IMPORTS 
* io: file reading 
* javax.sound.sampled: sound playing
*/

import java.io.*;
import javax.sound.sampled.*;

// class that plays, stops and reads the music
public class MusicPlayer {
    // attributes 
    Long currentFrame;
    Clip clip;

    AudioInputStream audioInputStream;
    static String filePath;

    // constructor to initialize streams and clip
    public MusicPlayer(Boolean loopInfinite)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        if (loopInfinite) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            // plays once
            clip.loop(0);

        }
    }

    // play the music
    public static void playSound(String relativePath, Boolean loopInfinite) {
        // gets the path and trys to read this sound file to play
        try {
            filePath = "sounds/" + relativePath;
            MusicPlayer audioPlayer = new MusicPlayer(loopInfinite);
            audioPlayer.play();
            while (true) {
                if (!loopInfinite
                        && audioPlayer.clip.getMicrosecondPosition() == audioPlayer.clip.getMicrosecondLength()) {
                    audioPlayer.stop();
                    break;
                }
            }
            // audioPlayer.stop
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // Method to play the audio
    public void play() {
        // start the clip
        clip.start();
    }

    // to stop the music from playing
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
    }

}
