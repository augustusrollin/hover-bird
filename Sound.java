import java.awt.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private String soundFilePath;
    private AudioInputStream audioStream;
    public static Clip clip;
    String audioName;

    /**
     * Plays the specified audio file.
     *
     * @param audioName the path to the audio file
     * @throws AWTException                  if an abstract window toolkit exception occurs
     * @throws UnsupportedAudioFileException if the audio file format is not supported
     * @throws IOException                   if an I/O exception occurs
     * @throws LineUnavailableException      if a line to the audio system is unavailable
     */
    public void playSound(String audioName)
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        this.audioName = audioName;
        
        // Create a file object from the audio file path
        File file = new File(audioName);
        
        // Obtain an audio input stream from the file
        audioStream = AudioSystem.getAudioInputStream(file);
        
        // Get a clip from the audio system
        clip = AudioSystem.getClip();
        
        // Open the audio clip with the audio input stream
        clip.open(audioStream);
        
        // Get the gain control to adjust the volume
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        
        // Adjust the volume based on the audio name
        if (audioName.equals("music/crazyMusic.wav")) {
            gainControl.setValue(-25.0f);
        } else if (audioName.equals("music/explosionSound1.wav")) {
            gainControl.setValue(6.0f);
        }
        
        // Start playing the audio clip
        clip.start();
    }
}
