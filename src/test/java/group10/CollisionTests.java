package group10;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class CollisionTests {
    private Player pl;
    private ArrayList<Entity> entities;
    private CollisionDetector  cols;
    private Wall w;

    final int size = Gamepanel.tileSize;
    @BeforeEach
    public void setup () {
        pl = new Player(0,0, new KeyboardHandler());
        entities = new ArrayList<>();
        w = new Wall(0, 2*size);
        cols = new CollisionDetector();
    }

    @Test
    public void collision(){
        pl.setPos(size, 2*size);
        w.setPos(size, 2*size);
        boolean check = cols.collide(pl, w);
        assertTrue(check);
    }

    @Test
    public void NotaCollision(){
        pl.setPos(size, 3*size);
        w.setPos(size, 2*size);
        boolean check = cols.collide(pl, w);
        assertFalse(check);
    }

    @Test
    public void NoXCollision(){
        pl.setPos(size, 3 * size);
        w.setPos(2 * size, 3 * size);
        assertFalse(cols.collide(pl, w));
    }

    @Test
    public void correctIndexcheck(){
        pl.setPos(size, 4*size);
        w.setPos(size, 4*size);
        entities.add(w);
        int index = cols.collideIndex(pl, entities);
        assertEquals(index, 0);
    }

    @Test
    public void correctIndexcheck2(){
        pl.setPos(size, 4*size);
        w.setPos(size, 3*size);
        entities.add(w);
        int index = cols.collideIndex(pl, entities);
        assertEquals(index, -1);
    }

    @Test
    public void correctIndexNotStart () {
        pl.setPos(2 * size,0);
        for (int i = 0; i < 4; i++) {
            this.entities.add(new Wall(i * size, 0));
        }
        assertEquals(2, cols.collideIndex(pl, entities));
    }



}