import java.awt.Image;
import java.util.Random;

public class Obstacles {

    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 15;

    public String orientation;

    private Image image;

    public Obstacles(String orientation) {
        this.orientation = orientation;
        reset();
    }

    public void reset() {
        width = 66;
        height = 400;
        x = Window.WIDTH + 2;
        Random rand = new Random();
        if (orientation.equals("south")) {
            // y = -(int) (Math.random() * 120) - height / 4;
            y = (int) (rand.nextInt(201) + rand.nextInt(201)) - height / 2;
            //y = 0;
        }
    }

    public void update() {
        x -= speed;
    }

    public boolean collides(int _x, int _y, int _width, int _height) {

        int margin = 2;

        if (_x + _width - margin > x && _x + margin < x + width) {

            if (orientation.equals("south") && _y < y + height) {
                return true;
            } else if (orientation.equals("north") && _y + _height > y) {
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
