import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Dimension;

public class Characters {
    public Point position;
    public Dimension boundingBox;
    public boolean isDead;

    public static boolean boosted;
    private String imageName;
    public static int sensitivity;
    private int jumpDelay;
    private double rotation;
    public static int rocketFuel = 400;
    public static Image image;
    private Keyboard keyboard;

    public Characters(String imageName, Dimension boundingBox) {
        /*
         * A higher value of y shifts the birds initial postition lower on the screen
         * A higher x shifts the birds initial position towards the right side of the
         * screen
         * Width and height match the images dimensions in order to detect any
         * collisions, and it affects the jumps
         * if the width and height are not properly recorded
         */
        // Initialize the character's position, bounding box, rotation, jump delay, and death status
        position = new Point(
                Integer.parseInt(Window.prop.getProperty("character.x")),
                Integer.parseInt(Window.prop.getProperty("character.y")));
        this.boundingBox = boundingBox;
        rotation = Float.parseFloat(Window.prop.getProperty("character.rotation"));
        jumpDelay = Integer.parseInt(Window.prop.getProperty("character.jumpDelay"));
        isDead = Boolean.parseBoolean(Window.prop.getProperty("character.isDead"));
        this.imageName = imageName;
        // Set the sensitivity, boosted boolean, and obtain the keyboard instance
        sensitivity = 9;
        boosted = false;
        keyboard = Keyboard.getInstance();
    }

    public void update() {
        // Update the character's position based on keyboard inputs
        
        // Decrement the jump delay
        if (jumpDelay > 0)
            jumpDelay--;

        // Move the character down if the down key is pressed and there is no jump delay
        if (!isDead && keyboard.isDown(KeyEvent.VK_DOWN) && jumpDelay <= 0) {
            position.y += sensitivity;
            jumpDelay = 1;
        } 
        // Move the character up if the up key is pressed and there is no jump delay
        else if (!isDead && keyboard.isDown(KeyEvent.VK_UP) && jumpDelay <= 0) {
            position.y -= sensitivity;
            jumpDelay = 1;
        } 
        // Activate boost if the space key is pressed, there is no jump delay, the game has started, and the game time is greater than 5000
        else if (!isDead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0 && GameRunner.started
                && GamePanel.gameTime > 5000) {
            // Check if there is remaining rocket fuel
            if (rocketFuel > 0) {
                Obstacles.characterBoost = 11;
                image = Util.loadImage("images/" + "boostedSpaceship" + ".png");
                boosted = true;
                sensitivity = 13;
                GameRunner.PIPE_DELAY = (int) (80 / ((Obstacles.speed + Obstacles.characterBoost) / 9));
            } else {
                Obstacles.characterBoost = 0;
            }
            jumpDelay = 1;
        }
    }

    public Render getRender() {
        Render r = new Render();
        r.x = position.x;
        r.y = position.y;
    
        // Load the image if it hasn't been loaded yet
        if (image == null) {
            image = Util.loadImage("images/" + imageName + ".png");
        }
        r.image = image;
    
        rotation = 0;
        // Limit the rotation to a maximum of PI/2
        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;
    
        r.transform = new AffineTransform();
        // Translate the transform to the center of the character's bounding box
        r.transform.translate(position.x + boundingBox.width / 2, position.y + boundingBox.height / 2);
        // Rotate the transform
        r.transform.rotate(rotation);
        // Translate the transform back to the top-left corner of the bounding box
        r.transform.translate(-1 * boundingBox.width / 2, -1 * boundingBox.height / 2);
    
        return r;
    }
    
}
