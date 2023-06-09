import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Dimension;

public class Character {
    public Point position;
    public Dimension boundingBox;
    public boolean isDead;

    public static boolean boosted;
    private String imageName;
    public static int sensitivity;
    public static int rocketFuel = 100;
    public static Image image;
    private Keyboard key;

    public Character(String imageName, Dimension boundingBox) {
        /*
         * A higher value of y shifts the birds initial postition lower on the screen
         * A higher x shifts the birds initial position towards the right side of the
         * screen
         * Width and height match the images dimensions in order to detect any
         * collisions, and it affects the jumps
         * if the width and height are not properly recorded
         */
        // Initialize the character's position, bounding box, rotation, jump delay, and
        // death status
        position = new Point(
                Integer.parseInt(HoverBird.prop.getProperty("character.x")),
                Integer.parseInt(HoverBird.prop.getProperty("character.y")));
        this.boundingBox = boundingBox;
        isDead = Boolean.parseBoolean(HoverBird.prop.getProperty("character.isDead"));
        this.imageName = imageName;
        // Set the sensitivity, boosted boolean, and obtain the key instance
        sensitivity = 9;
        boosted = false;
        key = Keyboard.getInstance();
    }

    // Update the character's position based on key inputs
    public void update() {
        // Move the character down if the down key is pressed
        if (!isDead && key.isDown(KeyEvent.VK_DOWN)) {
            position.y += sensitivity;
        }
        // Move the character up if the up key is pressed
        else if (!isDead && key.isDown(KeyEvent.VK_UP)) {
            position.y -= sensitivity;
        }
        // Activate boost if the space key is pressed, the game has started, and the
        // game time is greater than 5000
        else if (!isDead && key.isDown(KeyEvent.VK_SPACE) && GameRunner.started) {
            // Check if there is remaining rocket fuel
            if (rocketFuel > 0) {
                Obstacle.characterBoost = 11;
                image = ImageUtil.loadImage("images/" + Mode.boostedImage + ".png");
                boosted = true;
                sensitivity = 13;
                GameRunner.PIPE_DELAY = (int) (80 / ((Obstacle.speed + Obstacle.characterBoost) / 9));
            } else {
                Obstacle.characterBoost = 0;
            }
        }
    }

    public ImageRender getImageRender() {
        ImageRender imgRender = new ImageRender();
        imgRender.x = position.x;
        imgRender.y = position.y;

        // Load the image if it hasn't been loaded yet
        if (image == null) {
            image = ImageUtil.loadImage("images/" + imageName + ".png");
        }
        imgRender.image = image;

        imgRender.transform = new AffineTransform();
        // Translate the transform to the center of the character's bounding box
        imgRender.transform.translate(position.x + boundingBox.width / 2, position.y + boundingBox.height / 2);

        // Translate the transform back to the top-left corner of the bounding box
        imgRender.transform.translate(-1 * boundingBox.width / 2, -1 * boundingBox.height / 2);

        return imgRender;
    }
}
