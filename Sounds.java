import java.awt.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {
    private String soundFilePath;
    private AudioInputStream audioStream;
    public static Clip clip;
    String audioName;

    public void playSound(String audioName)
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.audioName = audioName;
        File file = new File(audioName);
        audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        if (audioName.equals("music/crazyMusic.wav")) {
            gainControl.setValue(6.0f);
        } else if (audioName.equals("music/explosionSound1.wav")) {
            gainControl.setValue(6.0f);
        }
        clip.start();
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }

    public void setSoundFilePath(String soundFilePath) {
        soundFilePath = soundFilePath;
    }
}
