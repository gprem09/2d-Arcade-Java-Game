package group10;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class WinTileTest {
    @Test
    public void stepTest () {
        WinTile s = new WinTile(0, 0);
        s.stepOn();
        assertTrue(s.on);
    }
}