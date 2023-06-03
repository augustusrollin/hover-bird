import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Dimension;

//birds
public class Characters {
    public Point position;
    public Dimension boundingBox;
    public boolean isDead;

    public static boolean boosted;
    private String imageName;
    private double yVelocity;
    private double gravity;
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
        position = new Point(
                Integer.parseInt(Window.prop.getProperty("character.x")),
                Integer.parseInt(Window.prop.getProperty("character.y")));
        this.boundingBox = boundingBox;
        // boundingBox = new Dimension(
        // Integer.parseInt(Window.prop.getProperty("character.width")),
        // Integer.parseInt(Window.prop.getProperty("character.height"))
        // );
        rotation = Float.parseFloat(Window.prop.getProperty("character.rotation"));
        yVelocity = Integer.parseInt(Window.prop.getProperty("character.yVelocity"));
        gravity = Float.parseFloat(Window.prop.getProperty("character.gravity"));
        jumpDelay = Integer.parseInt(Window.prop.getProperty("character.jumpDelay"));
        isDead = Boolean.parseBoolean(Window.prop.getProperty("character.isDead"));
        this.imageName = imageName;
        sensitivity = 9;
        boosted = false;
        keyboard = Keyboard.getInstance();
    }

    public void update() {
        // yVelocity += gravity;
        // boolean resetBoost = false;
        if (jumpDelay > 0)
            jumpDelay--;

        if (!isDead && keyboard.isDown(KeyEvent.VK_DOWN) && jumpDelay <= 0) {
            // yVelocity = -10;
            position.y += sensitivity;
            jumpDelay = 1;
        } else if (!isDead && keyboard.isDown(KeyEvent.VK_UP) && jumpDelay <= 0) {
            position.y -= sensitivity;
            jumpDelay = 1;
        } else if (!isDead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0 && GameRunner.started
                && GamePanel.gameTime > 5000) {
            if (rocketFuel > 0) {
                Obstacles.characterBoost = 11;
                image = Util.loadImage("images/" + "boostedSpaceship" + ".png");
                boosted = true;
                sensitivity = 13;
                GameRunner.PIPE_DELAY = (int) (80 / ((Obstacles.speed + Obstacles.characterBoost) / 9));
                // imageName = "monkeyBackground";
            } else {
                Obstacles.characterBoost = 0;
            }
            jumpDelay = 1;
        }
        // position.y += (int) yVelocity;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = position.x;
        r.y = position.y;

        if (image == null) {
            image = Util.loadImage("images/" + imageName + ".png");
            // boundingBox = new Dimension(image.getWidth(null), image.getHeight(null));
            // System.out.println(boundingBox);
        }
        r.image = image;

        // rotation = (90 * (yVelocity + 20) / 20) - 90;
        // rotation = rotation * Math.PI / 180;
        rotation = 0;
        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        r.transform = new AffineTransform();
        r.transform.translate(position.x + boundingBox.width / 2, position.y + boundingBox.height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-1 * boundingBox.width / 2, -1 * boundingBox.height / 2);

        return r;
    }
}
