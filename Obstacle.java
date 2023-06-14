import java.util.Random;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Point;
import java.awt.Dimension;

public class Obstacle {
    public Point position;
    public Dimension boundingBox;
    public static Level level;
    public static int speed = 20;
    public static double characterBoost = 0;

    public String orientation;
    private Image image;
    public String imageName;

    public Obstacle(String orientation, String imageName) {
        this.orientation = orientation;
        position = new Point(0, 0);
        boundingBox = new Dimension(0, 0);
        this.imageName = imageName;
        reset();
    }

    /**
     * Resets the obstacles to its initial state.
     */
    public void reset() {
        boundingBox.width = 66;
        boundingBox.height = 400;
        position.x = HoverBird.WIDTH + 2;
        Random rand = new Random();
        if (orientation.equals("south")) {
            position.y = (int) (rand.nextInt(201) + rand.nextInt(201)) - boundingBox.height / 2;
        }
    }

    /**
     * Updates the position of the obstacles based on the current speed and
     * character boost.
     */
    public void update() {
        position.x -= (speed + characterBoost);
    }

    /**
     * Checks if the character collides with the obstacles.
     *
     * @param characterPosition  the position of the character
     * @param characterDimension the dimensions of the character
     * @return true if collision occurs, false otherwise
     */
    public boolean collides(Point characterPosition, Dimension characterDimension) {

        int margin = 2;
        Sound audioPlayer = new Sound();
        if (characterPosition.x + characterDimension.width - margin > position.x &&
                characterPosition.x + margin < position.x + boundingBox.width) {
            if (orientation.equals("south") &&
                    (characterPosition.y < position.y + boundingBox.height)) {
                try {
                    Sound.clip.stop();
                    audioPlayer.playSound("music/explosionSound1.wav");
                    audioPlayer.playSound("music/crazyMusic.wav");
                    Sound.clip.stop();
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
            } else if (orientation.equals("north") &&
                    (characterPosition.y + characterDimension.height > position.y)) {
                try {
                    Sound.clip.stop();
                    audioPlayer.playSound("music/explosionSound1.wav");
                    audioPlayer.playSound("music/crazyMusic.wav");
                    Sound.clip.stop();
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
            }
        }
        return false;
    }

    /**
     * Returns the Render object representing the obstacles for rendering.
     *
     * @return the Render object for the obstacles
     */
    public ImageRender getImageRender() {
        ImageRender imgRender = new ImageRender();
        imgRender.x = position.x;
        imgRender.y = position.y;

        if (image == null) {
            image = ImageUtil.loadImage("images/" + imageName + orientation + ".png");
        }
        imgRender.image = image;

        return imgRender;
    }
}
