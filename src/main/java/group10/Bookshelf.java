package group10;

import javax.imageio.ImageIO;

/**
 * <code>Bookshelf</code>
 * Barrier within map.
 */
public class Bookshelf extends Barrier {
    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     * @throws Exception if image file is not found or cannot be read
     */
    Bookshelf (int x, int y) {
        super(x, y, "bookshelf");
    }
}
