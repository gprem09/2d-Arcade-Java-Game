package group10;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <code>Entity</code>
 * abstract class for all game objects.
 */
public abstract class Entity {
    int x, y;
    int size = Gamepanel.tileSize;
    BufferedImage image;
    /**
     * <code>setPos</code>
     * sets the position of the entity.
     * @param x the new x position
     * @param y the new y position
     */
    public void setPos (int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * <code>draw</code>
     * draws the entity on the graphics at its position.
     * @param GFX the graphics to draw on.
     * @see java.awt.Graphics2D
     */
    public void draw (Graphics2D GFX) {
        GFX.drawImage(image, x, y, size, size, null);
    }
}
