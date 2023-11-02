package group10;

import java.util.ArrayList;

/**
 * <code>Animate</code>
 * Extension of the Entity class for moving classes.
 * @see Entity
 */
public abstract class Animate extends Entity{
    int speed = Gamepanel.tileSize;

    /**
     * <code>update</code>
     * Makes changes to components of Animate.
     * @param entities which entities to check collision with.
     */
    public void update (ArrayList<Entity> entities) {};
}
