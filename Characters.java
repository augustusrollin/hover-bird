import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

//birds
public class Characters {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean isDead;

    private String imageName;
    private double yVelocity;
    private double gravity;
    private int jumpDelay;
    private double rotation;
    private Image image;
    private Keyboard keyboard;

    public Characters() {
        /*
         * A higher value of y shifts the birds initial postition lower on the screen
         * A higher x shifts the birds initial position towards the right side of the
         * screen
         * Width and height match the images dimensions in order to detect any
         * collisions, and it affects the jumps
         * if the width and height are not properly recorded
         */
        x = Integer.parseInt(Window.prop.getProperty("character.x"));
        y = Integer.parseInt(Window.prop.getProperty("character.y"));
        width = Integer.parseInt(Window.prop.getProperty("character.width"));
        height = Integer.parseInt(Window.prop.getProperty("character.height"));
        rotation = Float.parseFloat(Window.prop.getProperty("character.rotation"));
        yVelocity = Integer.parseInt(Window.prop.getProperty("character.yVelocity"));
        gravity = Float.parseFloat(Window.prop.getProperty("character.gravity"));
        jumpDelay = Integer.parseInt(Window.prop.getProperty("character.jumpDelay"));
        isDead = Boolean.parseBoolean(Window.prop.getProperty("character.isDead"));
        imageName = Window.prop.getProperty("character.imageName");

        keyboard = Keyboard.getInstance();
    }

    public void update() {
        // yVelocity += gravity;
        if (jumpDelay > 0)
            jumpDelay--;

        if (!isDead && keyboard.isDown(KeyEvent.VK_DOWN) && jumpDelay <= 0) {
            // yVelocity = -10;
            y += 10;
            jumpDelay = 1;
        } else if (!isDead && keyboard.isDown(KeyEvent.VK_UP) && jumpDelay <= 0) {
            y -= 10;
            jumpDelay = 1;
        }
        // y += (int) yVelocity;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("images/" + imageName + ".png");
        }
        r.image = image;

        // rotation = (90 * (yVelocity + 20) / 20) - 90;
        // rotation = rotation * Math.PI / 180;
        rotation = 0;
        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-width / 2, -height / 2);

        return r;
    }
}
