package agh.cs.lab2Home;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class Vector2dTest {
    private Vector2d v_1_1 = new Vector2d(1, 1);
    private Vector2d v_0_0 = new Vector2d(0, 0);

    @Test
    public void Vector2dEqualsTest() {
        assertTrue(v_1_1.equals(new Vector2d(1, 1)));
        assertFalse(v_1_1.equals(new Vector2d(-1, -1)));
    }

    @Test
    public void Vector2dToStringTest() {
        assertEquals(v_1_1.toString(), "(1, 1)");
        assertEquals(new Vector2d(0, -1).toString(), "(0, -1)");
    }

    @Test
    public void Vector2dPrecedesTest() {
        assertTrue(v_0_0.precedes(v_1_1));
        assertTrue(v_1_1.precedes(new Vector2d(1, 2)));
        assertTrue(v_1_1.precedes(v_1_1));
        Vector2d v_2_5 = new Vector2d(2, 5);
        assertFalse(v_2_5.precedes(new Vector2d(4, 4)));
    }

    @Test
    public void Vector2dFollowsTest() {
        assertFalse(v_0_0.follows(v_1_1));
        assertFalse(v_1_1.follows(new Vector2d(1, 2)));
    }

    @Test
    public void Vector2dUpperRightTest() {
        assertEquals(v_0_0.upperRight(v_1_1), v_1_1);
        assertEquals(v_0_0.upperRight(new Vector2d(-1, 1)), new Vector2d(0,1));
    }

    @Test
    public void Vector2dLowerLeftTest() {
        assertEquals(v_0_0.lowerLeft(v_1_1), v_0_0);
        assertEquals(v_1_1.lowerLeft(new Vector2d(0, 2)), new Vector2d(0, 1));
    }

    @Test
    public void Vector2dAddTest() {
        assertEquals(v_1_1.add(v_0_0), v_1_1);
        assertEquals(v_1_1.add(v_1_1), new Vector2d(2, 2));
    }

    @Test
    public void Vector2dSubtractTest() {
        assertEquals(v_1_1.subtract(v_0_0), v_1_1);
        assertEquals(v_1_1.subtract(v_1_1), v_0_0);
    }

    @Test
    public void Vector2dOppositeTest() {
        assertEquals(v_0_0.opposite(), v_0_0);
        assertEquals(new Vector2d(-1, -1).opposite(), v_1_1);
    }
}