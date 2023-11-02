package group10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class PunishmentTest {
    private Punishment p;
    @BeforeEach
    public void setUP () {
        p = new Punishment(0,0);
    }
    @Test
    public void activityTest () {
        p.setInactive();
        assertFalse(p.active);
    }
    @Test
    public void scoreTest () {
        int value = 500;
        p.setScore(value);
        int x = p.setInactive();
        assertEquals(value, x);
    }
}