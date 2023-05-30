import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Window {

    static Properties prop;

    public static int WIDTH;
    public static int HEIGHT;

    public static void main(String[] args)
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        try (InputStream input = new FileInputStream("config.properties")) {
            prop = new Properties();

            // load a properties file
            prop.load(input);
            WIDTH = Integer.parseInt(prop.getProperty("window.WIDTH"));
            HEIGHT = Integer.parseInt(prop.getProperty("window.HEIGHT"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JFrame frame = new JFrame();
        // frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLocation(-10, 0);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
    }
}
