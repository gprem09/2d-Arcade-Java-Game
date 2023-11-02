package group10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BasicRewardTest {
    Reward r;
    @BeforeEach
    public void setUp() {
        r = new BasicReward(0,0);
    }
    @Test
    public void scoreCollectTest() {
        r.setValue(500);
        assertEquals(500,r.collect());
    }
    @Test
    public void deactivateTest() {
        r.collect();
        assertTrue(r.collected);
    }
}