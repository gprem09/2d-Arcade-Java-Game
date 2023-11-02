package group10;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * <code>Reward</code>
 * Abstract reward class.
 */
public abstract class Reward extends Entity {
    Boolean collected = false;
    Boolean visible;
    int value = 100;

    /**
     * <code>draw</code>
     * only outputs the reward to a screen if it has not been collected yet.
     * @param g the graphics to draw on.
     */
//    public void draw (Graphics2D g) {
//        if (!collected) {
//            g.drawImage(image, x, y, size, size, null);
//        }
//    }

    String img_path;
    String reward_type = "";
    Reward (int x, int y, String reward_type) {
        this.x = x;
        this.y = y;
        this.img_path = "/rewards/" + reward_type;
        if (reward_type == "basic_reward") {
            this.img_path = this.img_path + "_1.png";
        } else {
            this.img_path = this.img_path + "_1.jpg";
        }
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream((img_path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g){
        if ( visible && !collected) {
            g.drawImage(image, x, y, size, size, null);
        }
    }

    /**
     * <code>collect</code>
     * collects the reward, deactivating it.
     * @return the number of points to be added for collection of the reward.
     */
    public int collect () {
        if (!this.collected) {
            this.collected = true;
            return this.value;
        }
        return 0;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
