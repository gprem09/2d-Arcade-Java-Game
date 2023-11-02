package group10;

import javax.imageio.ImageIO;

/**
 * <code>Chair</code>
 * Barrier within map.
 */
public class Chair extends Barrier {
    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     * @throws Exception if image file is not found or cannot be read
     */
    Chair (int x, int y) {
        super(x, y, "chair");
    }
}
