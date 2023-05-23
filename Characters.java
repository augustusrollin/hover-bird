import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.Properties;

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

    public Characters() {}

    public Characters(Properties prop) {
        /*
         * A higher value of y shifts the birds initial postition lower on the screen
         * A higher x shifts the birds initial position towards the right side of the screen
         * Width and height match the images dimensions in order to detect any collisions, and it affects the jumps
         * if the width and height are not properly recorded
         */
        x = Integer.parseInt(prop.getProperty("character.x")); 
        y = Integer.parseInt(prop.getProperty("character.y")); 
        width = Integer.parseInt(prop.getProperty("character.width"));
        height = Integer.parseInt(prop.getProperty("character.height"));
        rotation = Float.parseFloat(prop.getProperty("character.rotation"));
        yVelocity = Integer.parseInt(prop.getProperty("character.yVelocity"));
        gravity = Float.parseFloat(prop.getProperty("character.gravity"));
        jumpDelay = Integer.parseInt(prop.getProperty("character.jumpDelay"));
        isDead = Boolean.parseBoolean(prop.getProperty("character.isDead"));
        imageName = prop.getProperty("character.imageName");

        keyboard = Keyboard.getInstance();
    }

    public void update() {
        yVelocity += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (!isDead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
            yVelocity = -10;
            jumpDelay = 10;
        }

        y += (int)yVelocity;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("images/"+imageName+".png");     
        }
        r.image = image;

        rotation = (90 * (yVelocity + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-width / 2, -height / 2);

        return r;
    }
}
