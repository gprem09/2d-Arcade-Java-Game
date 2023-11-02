package group10;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * <code>BasicReward</code>
 * Extension of Reward. Stationary. Required for completion of the game.
 * @see Reward
 */
public class BasicReward extends Reward {
    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     */
    BasicReward (int x, int y) {
        super(x, y, "basic_reward");
        this.visible = true;
    }

}
