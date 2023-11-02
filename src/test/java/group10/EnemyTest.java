package group10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class EnemyTest {

    private Player pl;
    private ArrayList<Entity> entities;
    private Enemy e;
    final int size = Gamepanel.tileSize;

    @BeforeEach
    public void setUp() {
        pl = new Player(0,0, new KeyboardHandler());
        entities = new ArrayList<>();
        e = new Enemy(0, 0);
    }

    @AfterEach
    public void tearDown () {
        pl = null;
        entities = null;
        e = null;
    }

    @Test
    public void updateTestDelay() {
        e.setPos(0,0);
        pl.setPos(3 * size, 0);
        e.update(entities, pl);
        e.update(entities, pl);
        e.update(entities, pl);
        e.update(entities, pl);
        assertEquals(2 * size, e.x);
    }

    // go x
    @Test
    public void updateTestRight() {
        e.setPos(0,0);
        pl.setPos(size, 0);
        e.update(entities, pl);
        assertEquals(pl.x, e.x);
    }
    @Test
    public void updateTestLeft() {
        e.setPos(0,0);
        pl.setPos(-size, 0);
        e.update(entities, pl);
        assertEquals(pl.x, e.x);
    }

    // go y
    @Test
    public void updateTestUp() {
        e.setPos(0,0);
        pl.setPos(0, -size);
        e.update(entities, pl);
        assertEquals(pl.y, e.y);
    }
    @Test
    public void updateTestDown() {
        e.setPos(0,0);
        pl.setPos(0, size);
        e.update(entities, pl);
        assertEquals(pl.y, e.y);
    }

    // want x, wall, diffy -> go y
    @Test
    public void updateTestHorizontalCollisionDiffY() {
        e.setPos(0,0);
        pl.setPos(2 * size,size);
        entities.add(new Wall(size, 0));
        e.update(entities, pl);
        assertEquals(pl.y, e.y);
    }

    // want y, wall, diffx -> go x
    @Test
    public void updateTestVerticalCollisionDiffX() {
        e.setPos(0,0);
        pl.setPos(size,2 * size);
        entities.add(new Wall(0, size));
        e.update(entities, pl);
        assertEquals(pl.x, e.x);
    }

    // want x, wall, same y,
    @Test
    public void updateTestHorizonalCollisionSameY() {
        e.setPos(0,0);
        pl.setPos(2 * size,0);
        entities.add(new Wall(size, 0));
        e.update(entities, pl);
        assertTrue((e.x == 0 && e.y == 0));
    }

    // want y, same x, go x
    @Test
    public void updateTestVerticalCollisionSameX() {
        e.setPos(0,0);
        pl.setPos(0,2 * size);
        entities.add(new Wall(0, size));
        e.update(entities, pl);
        assertTrue((e.x == 0 && e.y == 0));
    }

}