import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Render {
    public int x;
    public int y;

    public Image image;
    public AffineTransform transform;

    public Render() {
        // Default constructor
    }

    /**
     * Constructs a Render object with the specified position and image.
     *
     * @param x          the x-coordinate of the position
     * @param y          the y-coordinate of the position
     * @param imagePath  the path to the image file
     */
    public Render(int x, int y, String imagePath) {
        // Synchronize with the underlying native toolkit
        Toolkit.getDefaultToolkit().sync();
        this.x = x;
        this.y = y;
        this.image = Util.loadImage(imagePath);
    }
}
