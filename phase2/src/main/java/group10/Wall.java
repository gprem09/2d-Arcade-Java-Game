package group10;

import javax.imageio.ImageIO;

/**
 * <code>Wall</code>
 * barrier surrounding the map.
 */
public class Wall extends Barrier {
    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     * @throws Exception if image file is not found or cannot be read
     */
    Wall (int x, int y) {

        super(x, y, "wall");
    }
}
