import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
//birds
public class Characters {

    public int x;
    public int y;
    public int width;
    public int height;

    public String imageName;

    public boolean isDead;

    public double yvelocity;
    public double gravity;

    private int jumpDelay;
    private double rotation;

    private Image image;
    private Keyboard keyboard;

    public Characters() {
        x = 100;
        y = 150;
        yvelocity = 0;
        width = 45;
        height = 32;
        gravity = 0.5;
        jumpDelay = 0;
        rotation = 5.0;
        isDead = false;
        imageName = "bird";

        keyboard = Keyboard.getInstance();
    }

    public void update() {
        yvelocity += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (!isDead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
            yvelocity = -10;
            jumpDelay = 10;
        }

        y += (int)yvelocity;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("images/"+imageName+".png");     
        }
        r.image = image;

        rotation = (90 * (yvelocity + 20) / 20) - 90;
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
