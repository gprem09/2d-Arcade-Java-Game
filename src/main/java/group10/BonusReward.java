package group10;

import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 * <code>BonusReward</code>
 * Special implementation of reward that disappears and reappears at 'random' positions.
 * @see Reward
 */
public class BonusReward extends Reward {
    int counter = 0;

    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     */
    BonusReward (int x, int y) {
        super(x, y, "bonus_reward");
        this.value = 250;
        this.visible = false;
    }

    /**
     * <code>update</code>
     * After a certain amount of time, change visibility and position of the reward.
     * @param entities the list of entities to check for collision.
     */
    public void update (ArrayList<Entity> entities) {
        counter = (counter + 1) % 15;
        if (counter == 0) {
            CollisionDetector x = new CollisionDetector();
            visible = !visible;
            int i = 0;
            do {
                this.x = (randomPositiveInt(Gamepanel.tileCols - 2) + 1) * Gamepanel.tileSize;
                this.y = (randomPositiveInt(Gamepanel.tileRows - 2) + 1) * Gamepanel.tileSize;
                i++;
            } while (x.collideIndex(this, entities) != -1 && i < 5);

            if (x.collideIndex(this, entities) != -1) {
                this.x = 20 * Gamepanel.tileSize;
                this.y = 2 * Gamepanel.tileSize;
            }
        }
    }

    /**
     * <code>randomPositiveInt</code>
     * creates a number between 0 and mod.
     * @param mod the largest number
     * @return the "random" number
     */
    private int randomPositiveInt(int mod) {
        int a = (int) System.nanoTime() % mod;
        return (a > 0) ? a : -a;
    }


}
