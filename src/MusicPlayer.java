import java.io.*;
import javax.sound.sampled.*;

public class MusicPlayer {
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

    public static void playSound(String relativePath, Boolean loopInfinite) {
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

    public static void main(String[] args) {
        playSound("disappointed.wav", false);
        
        playSound("miss.wav", true);
     
    }

    // Method to play the audio
    public void play() {
        // start the clip
        clip.start();
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
    }

}
