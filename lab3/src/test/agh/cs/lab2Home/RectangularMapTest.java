package agh.cs.lab2Home;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class RectangularMapTest {
    private final Vector2d v_1_1 = new Vector2d(1,1);

    @Test
    public void animalProperlyPlacedOnRectangularMapTest() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(map, v_1_1);
        map.place(animal);

        assertTrue(map.isOccupied(v_1_1));
        assertEquals(animal, map.objectAt(v_1_1));
    }

    @Test
    public void canMoveToOccupiedPositionOnRectangularMapTest() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(map, v_1_1);
        map.place(animal);

        assertFalse(map.canMoveTo(v_1_1));
    }

    @Test
    public void canMoveToPositionOutsideMapBorderOnRectangularMapTest() {
        IWorldMap map = new RectangularMap(10, 5);

        assertFalse(map.canMoveTo(new Vector2d(10,10)));
    }

    @Test
    public void canMoveToUnoccupiedPositionWithinMapBordersOnRectangularMapTest() {
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map, v_1_1));
        map.place(new Animal(map, new Vector2d(3,3)));

        assertTrue(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
        assertTrue(map.canMoveTo(new Vector2d(1,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    public void AnimalsNotRunningOnThemselvesOnRectangularMapTest () {
        IWorldMap map = new RectangularMap(10, 5);
        Animal a1 = new Animal(map, v_1_1);
        Animal a2 = new Animal(map, new Vector2d(1,0));
        map.place(a1);
        map.place(a2);

        map.run(new MoveDirection[] {MoveDirection.BACKWARD});

        assertEquals(a1, map.objectAt(v_1_1));
        assertEquals(a2, map.objectAt(new Vector2d(1,0)));
    }

    @Test
    public void AnimalsRunningProperlyOnRectangularMapTest() {
        MoveDirection[] directions = new OptionsParser().parse(new String[] {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        Animal a1 = new Animal(map);
        Animal a2 = new Animal(map,new Vector2d(3,4));
        map.place(a1);
        map.place(a2);
        map.run(directions);

        assertEquals(a1, map.objectAt(new Vector2d(2, 0)));
        assertEquals(a2, map.objectAt(new Vector2d(3, 4)));
    }
}
