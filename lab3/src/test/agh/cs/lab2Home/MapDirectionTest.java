package agh.cs.lab2Home;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MapDirectionTest {
    @Test
    public void MapDirectionNextTest() {
        assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
    }

    @Test
    public void MapDirectionPreviousTest() {
        assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
    }
}
