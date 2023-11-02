package group10;

import javax.imageio.ImageIO;

/**
 * <code>Table</code>
 * barrier within map.
 */
public class Table extends Barrier {
    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     * @throws Exception file is not found or cannot be read
     */
    Table (int x, int y) {
        super(x, y, "table");
    }
}
