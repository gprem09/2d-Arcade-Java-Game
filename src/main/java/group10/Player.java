package group10;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <code>Player</code>
 * the player class controlled by the user.
 * @see Animate
 */
public class Player extends Animate{
    BufferedImage image;
    KeyboardHandler key;
    int stuckCount = 0;
    int score = 0;
    int frame = 0;
    int trueFrame = 0;
    int prevX = 0, prevY = 0;
    Boolean alive = true;
    BufferedImage[][] images = new BufferedImage[4][3];
    /**
     * Class constructor given starting position and KeyboardHandler.
     * @param x the x position.
     * @param y the y position.
     * @param k the KeyboardHandler object.
     * @see KeyboardHandler
     */
    Player (int x, int y, KeyboardHandler k) {
        String[] directions = {"back", "front", "left", "right"};
        this.x = x;
        this.y = y;
        this.key = k;
        try {
            for (int i = 0; i < 12; i++) {
                this.images[i/3][i%3] = ImageIO.read(getClass().getResourceAsStream(("/player/player_" + directions[i/3] + "_" + ((i%3) + 1) + ".png")));
            }
            this.image = images[1][1];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>update</code>
     * makes changes to the player class based on the input from the keyboard.
     * @param entities which entities to check collision with.
     */
    public void update(ArrayList<Entity> entities) {
        if (stuckCount > 0) {
            stuckCount--;
            return;
        }
        if (key.up || key.left || key.right || key.down) {
            trueFrame = (trueFrame + 1) % 4;
            if (trueFrame == 3) { frame = 1;}
            else {frame = trueFrame;}
        }
        this.prevX = this.x;
        this.prevY = this.y;
        if (key.up) {
            this.y -= this.speed;
            this.image = this.images[0][frame];
        }
        if (key.down) {
            this.y += this.speed;
            this.image = this.images[1][frame];
        }
        if (key.left) {
            this.x -= this.speed;
            this.image = this.images[2][frame];
        }
        if (key.right) {
            this.x += this.speed;
            this.image = this.images[3][frame];
        }
        collisionHandler(entities);
    }

    /**
     * <code>collisionHandler</code>
     * Checks collision with the list of passed in entities and deals with each type of collision.
     * @param entities which entities to check collision with.
     */
    private void collisionHandler(ArrayList<Entity> entities) {
        CollisionDetector c = new CollisionDetector();
        int index = c.collideIndex(this, entities);
        if (index != -1){
            Entity collided = entities.get(index);
            if ((collided.getClass() == Wall.class) || (collided.getClass() == Table.class) || (collided.getClass() == Bookshelf.class) || (collided.getClass() == Chair.class) || (collided.getClass() == Desk.class)) {
                this.x = prevX;
                this.y = prevY;
                collisionHandler(entities);
            }
            else if (collided.getClass() == Punishment.class) {
                if (((Punishment) collided).active) {
                    this.score -= ((Punishment) collided).setInactive();
                    stuckCount = 4;
                }
            }
            else if (collided.getClass() == Enemy.class) {
                alive = false;
            }
            else if (collided.getClass() == BasicReward.class) {
                if (!((Reward) collided).collected) {
                    this.score += ((Reward)collided).collect();
                    entities.remove(collided);
                }
            }
            else if (collided.getClass() == BonusReward.class) {
                if (((BonusReward) collided).visible && !((Reward) collided).collected) {
                    this.score += ((Reward)collided).collect();
                    entities.remove(collided);
                }
            }
            else if (collided.getClass() == WinTile.class) {
                ((WinTile)collided).stepOn();
            }
        }
        if (this.score < 0) {
            this.alive = false;
        }
    }
    
    public void draw (Graphics2D GFX) {
        GFX.drawImage(image, x, y, this.size, this.size, null);
    }

}
