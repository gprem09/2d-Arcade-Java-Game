package group10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class GamePanelTest {
    Gamepanel game;
    @BeforeEach
    void setUpGamePanel() {
        game = new Gamepanel();
    }
    @Test
    void panelShouldBeEnabled() {
        assertTrue(game.isEnabled());
    }
    @Test
    void testEntitySetUp() {
        assertFalse(game.barrierList.isEmpty());
        assertFalse(game.basicRewards.isEmpty());
        assertFalse(game.bonusRewards.isEmpty());
        assertFalse(game.entityList.isEmpty());
    }
}
