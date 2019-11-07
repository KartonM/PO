package agh.cs.lab2Home;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

//Should be run only after OptionsParserTest, Vector2dTest and MapDirectionTest are completed.
//Tests assume that an animal initially faces NORTH at position (2, 2)
public class AnimalMovementTest {
    private final RectangularMap map;
    private final Vector2d initialPosition = new Vector2d(2, 2);

    public AnimalMovementTest() {
        map = new RectangularMap(5,5);
    }

    @Test
    public void animalInitializationTest() {
        Animal animal = new Animal(map, initialPosition);
        assertEquals(animal.getPosition(), new Vector2d(2, 2));
        assertEquals(animal.getFacedDirection(), MapDirection.NORTH);
    }

    @Test
    public void animalOrientationTest() {
        Animal animal = new Animal(map, initialPosition);
        moveAnimalAlongRoute(animal, new String[] {"r", "r", "f", "l", "r"});

        assertEquals(animal.getFacedDirection(), MapDirection.SOUTH);

        animal.move(MoveDirection.RIGHT);

        assertEquals(animal.getFacedDirection(), MapDirection.WEST);
    }

    @Test
    public void animalMovingOutsideWorldBorderTest() {
        Animal animal = new Animal(map, initialPosition);
        while(animal.getPosition().y < map.MAP_UPPER_RIGHT_CORNER.y) {
            animal.move(MoveDirection.FORWARD);
            System.out.println(animal);
        }

        //try to cross world border
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.getPosition().precedes(map.MAP_UPPER_RIGHT_CORNER));
    }

    @Test
    public void animalMovingToCorrectPositionTest() {
        Animal animal = new Animal(map, initialPosition);
        moveAnimalAlongRoute(animal, new String[] {"b", "b", "l", "f", "f"});
        assertEquals(animal.getPosition(), new Vector2d(0,0));
    }

    @Test
    public void animalWalkAroundBordersTest() {
        Animal animal = new Animal(map, initialPosition);
        moveAnimalAlongRoute(animal, new String[] {"f", "f", "l", "b", "b", "l"});
        assertEquals(map.MAP_UPPER_RIGHT_CORNER, animal.getPosition());
        moveAnimalAlongRoute(animal, new String[] {"f", "f", "f", "f", "r", "f", "f", "f", "f"});
        assertEquals(map.MAP_LOWER_LEFT_CORNER, animal.getPosition());
    }

    private void moveAnimalAlongRoute(Animal animal, String[] args) {
        MoveDirection[] route = OptionsParser.parse(args);
        System.out.println(Arrays.toString(route));
        for(MoveDirection dir : route) {
            animal.move(dir);
            //System.out.println(animal);
        }
    }
}
