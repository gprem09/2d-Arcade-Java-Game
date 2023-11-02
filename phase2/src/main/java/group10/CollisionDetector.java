package group10;

import java.util.ArrayList;

/**
 * <code>CollisionDetector</code>
 * Detects collision between Entities.
 */
public class CollisionDetector {
    /**
     * <code>collide</code>
     * @param a the first entity to check.
     * @param b the second entity to check.
     * @return boolean if the two entities collide.
     */
    public boolean collide (Entity a, Entity b) {
        return (a.x == b.x && a.y == b.y);
    }
    /**
     * <code>collideIndex</code>
     * @param entity the entity to check with collision in the list.
     * @param entities the array of entities to check.
     * @return the index of the entity in entities that collides with entity.
     */
    public int collideIndex (Entity entity, ArrayList<Entity> entities) {
        int len = entities.size();
        for (int i = 0; i < len; i++) {
            if (collide(entity,entities.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
