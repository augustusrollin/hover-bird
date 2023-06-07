import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    public static int gameTime = 0;
    private GameRunner game;
    private int width;
    private int height;

    public GamePanel() {
        game = new GameRunner();
        new Thread(this).start();
    }

    public GamePanel(int w, int h) {
        width = w;
        height = h;
    }

    public void update() {
        game.update();
        repaint();
    }

    @Override
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
        try {
            super.paintComponent(g);

            Graphics2D g2D = (Graphics2D) g;
            // Render all the objects in the game
            for (Render r : game.getRenders()) {
                if (r.transform != null)
                    g2D.drawImage(r.image, r.transform, null);
                else
                    g.drawImage(r.image, r.x, r.y, null);
            }

            g2D.setColor(Color.WHITE);

            if (!game.started) {
                // Display the start menu
                g2D.setFont(new Font("Futurism", Font.PLAIN, 40));
                g2D.drawString("Press SPACE to start", 650, 480);
                g2D.drawString("O for Original Mode", 650, 530);
                g2D.drawString("F for Future Mode", 650, 580);
                g2D.drawString("B for Rainbow Mode", 650, 630);
                g2D.drawString("H for Hell Mode", 650, 680);
            } else {
                // Display the score during gameplay
                g2D.setFont(new Font("Futurism", Font.PLAIN, 44));
                g2D.drawString(Integer.toString(game.score), 30, 60);
            }

            if (game.gameover) {
                // Display the game over message
                g2D.setFont(new Font("Futurism", Font.PLAIN, 40));
                g2D.drawString("Press R to restart", 650, 480);
            }
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(16); // increases the speed of the program
                gameTime += 16;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void lagMachine(int windows)
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

    public static void earBurst()
            throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Windows Xp Error Earrape.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
