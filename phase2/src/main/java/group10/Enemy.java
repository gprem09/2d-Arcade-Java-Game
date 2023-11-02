package group10;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <code>Enemy</code>
 * Moving enemy in game.
 */
public class Enemy extends Animate {
    char[] direction_list = {'u','d','l','r'};
    BufferedImage[][] images = new BufferedImage[4][3];
    char current_direction = direction_list[0];
    int delay_counter = 0;
    int trueFrame = 0;
    int frame = 0;
    /**
     * Class constructor specifying spawning position.
     * @param x the x position.
     * @param y the y position.
     */
    Enemy (int x, int y) {
        this.x = x;
        this.y = y;
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    this.images[i][j] = ImageIO.read(getClass().getResourceAsStream(("/enemies/teacher/t" + direction_list[i] + j + ".png")));
                }
            }
            this.image = this.images[1][1];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>abs</code>
     * Absolute value function
     * @param x the value which you need the absolute value of.
     * @return the absolute value of x.
     */
    private int abs(int x) {
        if (x < 0) {
            return -x;
        }
        return x;
    }

    /**
     * <code>update</code>
     * changes the current_direction of the enemy based on player position.
     * Then uses the movement handler to change position.
     * @param entities the list of entities to check collision.
     * @param player the player for the pathing.
     */
    public void update(ArrayList<Entity> entities, Player player) {
        if (delay_counter == 0) {
            int x_distance = player.x - this.x;
            int y_distance = player.y - this.y;
            if (abs(y_distance) > abs(x_distance)) {
                if (y_distance < 0) {
                    current_direction = direction_list[0];
                }
                else if (y_distance > 0) {
                    current_direction = direction_list[1];
                }
                y_distance -= player.y;
                y_distance *= -1; // now its just this.y
                move(entities);
                if (this.y == y_distance) {
                    if (x_distance < 0) {
                        current_direction = direction_list[2];
                    }
                    else if (x_distance > 0) {
                        current_direction = direction_list[3];
                    }
                    move(entities);
                }
            }
            else {
                if (x_distance < 0) {
                    current_direction = direction_list[2];
                }
                else if (x_distance > 0) {
                    current_direction = direction_list[3];
                }
                x_distance -= player.x;
                x_distance *= -1;
                move(entities);
                if (x_distance == this.x) {
                    if (y_distance < 0) {
                        current_direction = direction_list[0];
                    } else if (y_distance > 0) {
                        current_direction = direction_list[1];
                    }
                    move(entities);
                }
            }
            switch (current_direction) {
                case 'u':
                    image = images[0][frame];
                    break;
                case 'd':
                    image = images[1][frame];
                    break;
                case 'l':
                    image = images[2][frame];
                    break;
                case 'r':
                    image = images[3][frame];
                    break;
                default:
                    break;
            }
            trueFrame = (trueFrame + 1) % 4;
            if (trueFrame == 3) { frame = 1;}
            else {frame = trueFrame;}
        }
        delay_counter = (delay_counter + 1) % 3;
    }

    /**
     * <code>move</code>
     * changes the position based on the set current_direction.
     * @param entities which entities to check collision with.
     */
    private void move(ArrayList<Entity> entities) {
        CollisionDetector c = new CollisionDetector();
        int oldx = this.x, oldy = this.y;
        switch (current_direction) {
            case 'u':
                this.y -= this.speed;
                break;
            case 'd':
                this.y += this.speed;
                break;
            case 'l':
                this.x -= this.speed;
                break;
            case 'r':
                this.x += this.speed;
                break;
            default:
                break;
        }
        int ind = c.collideIndex(this, entities);
        if (ind != -1) {
            this.x = oldx;
            this.y = oldy;
        }
    }
}
