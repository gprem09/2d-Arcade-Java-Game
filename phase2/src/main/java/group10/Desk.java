package group10;

import javax.imageio.ImageIO;

/**
 * <code>Desk</code>
 * Barrier within map.
 */
public class Desk extends Barrier {
    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     * @throws Exception if image file is not found or cannot be read
     */
    Desk (int x, int y) {
        super(x, y, "desk");
    }
}
