package group10;

import javax.imageio.ImageIO;

/**
 * <code>Punishment</code>
 * non-moving punishment placed in the game.
 */
public class Punishment extends Entity {
    Boolean active = true;
    int score = 100;

    /**
     * Class Constructor with default position
     * @param x the starting x coordinate.
     * @param y the starting y coordinate.
     */
    Punishment (int x, int y) {
        this.x = x;
        this.y = y;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(("/enemies/punishment_1.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>setInactive</code>
     * turns off the punishment after being used.
     * @return the score to be removed after collision.
     */
    public int setInactive () {
        this.active = false;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(("/enemies/punishment_2.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.score;
    }

    /**
     * <code>setScore</code>
     * sets the score the punishment removes from the player.
     * @param score the score value of the punishment
     */
    public void setScore (int score) {
        this.score = score;
    }
}
