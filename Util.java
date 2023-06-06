import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Util {

    // HashMap to cache loaded images based on their file paths
    private static HashMap<String, Image> cache = new HashMap<String, Image>();

    /**
     * Loads an image from the specified file path. If the image has been previously loaded and cached, it is retrieved
     * from the cache instead of loading it again.
     *
     * @param path the file path of the image
     * @return the loaded image
     */
    public static Image loadImage(String path) {
        Image image = null;

        // Check if the image is already cached
        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        try {
            // Load the image from the file
            image = ImageIO.read(new File(path));

            // Cache the loaded image if it hasn't been cached before
            if (!cache.containsKey(path)) {
                cache.put(path, image);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
