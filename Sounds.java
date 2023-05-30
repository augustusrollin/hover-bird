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

    public void playSound(String audioName)
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(audioName);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        if (audioName.equals("crazyMusic.wav")) {
            gainControl.setValue(-25.0f);
        }
        clip.start();
    }

    void playSound() {
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }

    public void setSoundFilePath(String soundFilePath) {
        soundFilePath = soundFilePath;
    }
}
