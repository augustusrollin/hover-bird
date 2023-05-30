import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Obstacles {

    public int x;
    public int y;
    public int width;
    public int height;
    int horizDiff = 20;
    public static double speed = 20;
    public static double characterBoost = 0;

    public String orientation;

    private Image image;

    public Obstacles(String orientation) {
        this.orientation = orientation;
        reset();
    }

    public void reset() {
        width = 0;
        height = 400;
        x = Window.WIDTH + 2;
        Random rand = new Random();
        if (orientation.equals("south")) {
            // y = -(int) (Math.random() * 120) - height / 4;
            y = (int) (rand.nextInt(201) + rand.nextInt(201)) - height / 2;
            // y = 0;
            // y = 0;
        }
    }

    public void update() {
        x -= (speed + characterBoost);
        // speed += 0.1;
        // speed += 0.01;
    }

    public boolean collides(int _x, int _y, int _width, int _height) {
        int margin = 2;
        Sounds audioPlayer = new Sounds();
        if (_x + _width - margin > x && _x + margin < x + width) {
            if (orientation.equals("south") && _y < y + height) {
                try {
                    audioPlayer.playSound("explosionSound1.wav");
                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
                return true;
            } else if (orientation.equals("north") && _y + _height > y) {
                try {
                    audioPlayer.playSound("explosionSound1.wav");
                } catch (AWTException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

        return false;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            // image = Util.loadImage("images/pipe-" + orientation + ".png");
            image = Util.loadImage("images/cutskyscraperTest" + orientation + ".png");
        }
        r.image = image;

        return r;
    }
}
