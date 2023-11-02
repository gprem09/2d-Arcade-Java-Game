package group10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BonusRewardTest {
    BonusReward b;
    ArrayList<Entity> entities;
    @BeforeEach
    public void setUp () {
        b = new BonusReward(0, 0);
        entities = new ArrayList<>();
    }

    @Test
    public void visibilityTestInvisible () {
        b.update(entities);
        assertFalse(b.visible);
    }

    @Test
    public void visibilityTestVisible () {

        for (int i = 0; i < 15; i++) {
            b.update(entities);
        }
        assertTrue(b.visible);
    }
    @Test
    public void collect () {
        b.setValue(1000);
        assertEquals(1000, b.collect());
    }

    @Test
    public void noOpenPositions () {
        for (int x = 0; x < Gamepanel.tileCols; x++) {
            for (int y = 0; y < Gamepanel.tileRows; y++) {
                entities.add(new Wall(x * Gamepanel.tileSize, y * Gamepanel.tileSize));
            }
        }
        b.counter = 14;
        b.update(entities);
        assertEquals(20 * Gamepanel.tileSize, b.x);
        assertEquals(2 * Gamepanel.tileSize, b.y);
    }
}