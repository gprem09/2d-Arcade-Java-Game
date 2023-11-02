package group10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerTest {
    KeyboardHandler k;
    Player p;
    ArrayList<Entity> entities;
    int size = Gamepanel.tileSize;
    @BeforeEach
    public void setUp() {
        k = new KeyboardHandler();
        p = new Player(0,0,k);
        entities = new ArrayList<>();
    }
    @AfterEach
    public void tearDown () {
        k = null;
        p = null;
        entities = null;
    }

    @Test
    public void imagesLoaded () {
        for (BufferedImage[] x : p.images) {
            for (BufferedImage y : x) {
                assertNotNull(y);
            }
        }
    }

    @Test
    public void updateNoDirection() {
        p.update(entities);
        assertEquals(0, p.x);
        assertEquals(0, p.y);
    }

    @Test
    public void moveUpNoCollisions() {
        k.up = true;
        p.update(entities);
        assertEquals(-size, p.y);
    }
    @Test
    public void moveDownNoCollisions() {
        k.down = true;
        p.update(entities);
        assertEquals(size, p.y);
    }
    @Test
    public void moveLeftNoCollisions() {
        k.left = true;
        p.update(entities);
        assertEquals(-size, p.x);
    }
    @Test
    public void moveRightNoCollisions() {
        k.right = true;
        p.update(entities);
        assertEquals(size, p.x);
    }
    @Test
    public void moveCollideWall() {
        k.right = true;
        entities.add(new Wall(size, 0));
        p.update(entities);
        assertEquals(0, p.x);
    }
    @Test
    public void moveCollidePunishment() {
        k.right = true;
        entities.add(new Punishment(size, 0));
        p.update(entities);
        assertEquals(size, p.x);
        assertNotEquals(0, p.stuckCount);
    }
    @Test
    public void moveCollidePunishmentStuck() {
        k.right = true;
        entities.add(new Punishment(size, 0));
        p.update(entities); // 0 -> 4 stuck counter
        p.update(entities); // 4 -> 3
        p.update(entities); // 3 -> 2
        p.update(entities); // 2 -> 1
        p.update(entities); // 1 -> 0
        p.update(entities);
        assertEquals(2 * size, p.x);
    }
    @Test
    public void moveCollideEnemy() {
        k.right = true;
        entities.add(new Enemy(size, 0));
        p.update(entities);
        assertFalse(p.alive);
    }
    @Test
    public void moveCollideBasicRewardNotCollected() {
        k.right = true;
        BasicReward a = new BasicReward(size, 0);
        entities.add(a);
        p.update(entities);
        assertEquals(a.value, p.score);
    }
    @Test
    public void moveCollideBasicRewardCollected() {
        k.right = true;
        BasicReward a = new BasicReward(size, 0);
        a.collected = true;
        entities.add(a);
        p.update(entities);
        assertEquals(0, p.score);
    }
    @Test
    public void moveCollideBonusRewardInvisible() {
        k.right = true;
        BonusReward a = new BonusReward(size, 0);
        a.visible = false;
        entities.add(a);
        p.update(entities);
        assertEquals(0, p.score);
    }
    @Test
    public void moveCollideBonusRewardVisible() {
        k.right = true;
        BonusReward a = new BonusReward(size, 0);
        a.visible = true;
        entities.add(a);
        p.update(entities);
        assertEquals(a.value, p.score);
    }
    @Test
    public void moveCollideBonusRewardInactive() {
        k.right = true;
        BonusReward a = new BonusReward(size, 0);
        a.visible = true;
        a.collected = true;
        entities.add(a);
        p.update(entities);
        assertEquals(0, p.score);
    }

    @Test
    public void moveCollideWinTile() {
        k.right = true;
        WinTile a = new WinTile(size, 0);
        entities.add(a);
        p.update(entities);
        assertTrue(a.on);
    }

}