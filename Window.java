import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.awt.Point;


public class Window {

    static Properties prop;
    
    public static int WIDTH;
    public static int HEIGHT;
    public static void main(String[] args) {
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
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLocation(0,0);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
    }
    
}
