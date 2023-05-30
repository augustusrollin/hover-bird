import java.awt.Image;
import java.util.Random;
import java.awt.Point;
import java.awt.Dimension;

public class Obstacles {

    public Point position;
    public Dimension boundingBox;

    public int speed = 15;
    public String orientation;
    private Image image;

    public Obstacles(String orientation) {
        this.orientation = orientation;
        position = new Point(0, 0);
        boundingBox = new Dimension(0, 0);

        reset();
    }

    public void reset() {
        boundingBox.width = 66;
        boundingBox.height = 400;
        position.x = Window.WIDTH + 2;
        Random rand = new Random();
        if (orientation.equals("south")) {
            // y = -(int) (Math.random() * 120) - height / 4;
            position.y = (int) (rand.nextInt(201) + rand.nextInt(201)) - boundingBox.height / 2;
            // y = 0;
        }
    }

    public void update() {
        position.x -= speed;
    }

    /**
     * @param characterPosition
     * @param characterDimension
     * @return
     */
    public boolean collides(Point characterPosition, Dimension characterDimension) {

        int margin = 2;

        if (characterPosition.x + characterDimension.width - margin > position.x &&
                characterPosition.x + margin < position.x + boundingBox.width) {
            if (orientation.equals("south") &&
                (characterPosition.y < position.y + boundingBox.height)) {
                return true;
            } else if (orientation.equals("north") &&
                (characterPosition.y + characterDimension.height > position.y)
                ) {
                return true;
            }
        }

        return false;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = position.x;
        r.y = position.y;

        if (image == null) {
            // image = Util.loadImage("images/pipe-" + orientation + ".png");
            image = Util.loadImage("images/cutskyscraperTest" + orientation + ".png");
        }
        r.image = image;

        return r;
    }
}
