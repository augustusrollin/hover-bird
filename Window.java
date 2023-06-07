import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Window {

    static Properties prop; // Hold properties from the configuration file

    public static int WIDTH; // Store the width of the window
    public static int HEIGHT; // Store the height of the window

    public static void main(String[] args)
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        try (InputStream input = new FileInputStream("config.properties")) {
            prop = new Properties();

            // Load properties from the configuration file
            prop.load(input);

            // Retrieve and assign the width and height from the properties
            WIDTH = Integer.parseInt(prop.getProperty("window.WIDTH"));
            HEIGHT = Integer.parseInt(prop.getProperty("window.HEIGHT"));
        } catch (IOException ex) {
            ex.printStackTrace(); // Print the stack trace if there is an exception while loading properties
        }

        JFrame frame = new JFrame(); // Create a new JFrame object

        // Configure the window properties
        frame.setUndecorated(true); // Remove window decorations (title bar, buttons, etc.)
        frame.setVisible(true); // Make the window visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setLocation(-10, 0); // Set the initial window position

        Keyboard keyboard = Keyboard.getInstance(); // Get an instance of the Keyboard class
        frame.addKeyListener(keyboard); // Add the Keyboard object as a key listener to the frame

        GamePanel panel = new GamePanel(); // Create a new GamePanel object

        // Add the GamePanel object to the frame
        frame.add(panel);

        frame.setResizable(false); // Disable window resizing
        frame.setSize(WIDTH, HEIGHT); // Set the size of the window based on the WIDTH and HEIGHT variables
    }
}
