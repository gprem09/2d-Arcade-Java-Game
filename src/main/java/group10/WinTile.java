package group10;

import javax.imageio.ImageIO;

/**
 * <code>WinTile</code>
 * the win cell used to end the game.
 */
public class WinTile extends Entity {
    Boolean on = false;

    /**
     * Class Constructor with spawn position.
     * @param x the x spawn position.
     * @param y the y spawn position.
     * @throws Exception if image file is not found or cannot be read
     */
    WinTile(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(("/wintile/wintile.png")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>stepOn</code>
     * activates the winTile.
     */
    public void stepOn () {
        on = true;
    }
}
