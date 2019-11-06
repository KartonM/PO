package agh.cs.lab2Home;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

//Should be run only after OptionsParserTest, Vector2dTest and MapDirectionTest are completed.
//Tests assume that an animal initially faces NORTH at position (2, 2)
public class AnimalMovementTest {
    private final IWorldMap map;
    private final Vector2d innitialPosition = new Vector2d(2, 2);

    public AnimalMovementTest() {
        map = new RectangularMap(5,5);
    }

    @Test
    public void AnimalInitializationTest() {
        Animal animal = new Animal(map, innitialPosition);
        assertEquals(animal.getPosition(), new Vector2d(2, 2));
        assertEquals(animal.getFacedDirection(), MapDirection.NORTH);
    }

    @Test
    public void AnimalOrientationTest() {
        Animal animal = new Animal(map, innitialPosition);
        moveAnimalAlongRoute(animal, new String[] {"r", "r", "f", "l", "r"});

        assertEquals(animal.getFacedDirection(), MapDirection.SOUTH);

        animal.move(MoveDirection.RIGHT);

        assertEquals(animal.getFacedDirection(), MapDirection.WEST);
    }

    @Test
    public void AnimalMovingOutsideWorldBorderTest() {
        Animal animal = new Animal(map, innitialPosition);
        while(animal.getPosition().y < World.WORLD_UPPER_RIGHT_CORNER.y) {
            animal.move(MoveDirection.FORWARD);
            System.out.println(animal);
        }

        //try to cross world border
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.getPosition().precedes(World.WORLD_UPPER_RIGHT_CORNER));
    }

    @Test
    public void AnimalMovingToCorrectPositionTest() {
        Animal animal = new Animal(map, innitialPosition);
        moveAnimalAlongRoute(animal, new String[] {"b", "b", "l", "f", "f"});
        assertEquals(animal.getPosition(), new Vector2d(0,0));
    }

    @Test
    public void AnimalWalkAroundBordersTest() {
        Animal animal = new Animal(map, innitialPosition);
        moveAnimalAlongRoute(animal, new String[] {"f", "f", "l", "b", "b", "l"});
        assertEquals(World.WORLD_UPPER_RIGHT_CORNER, animal.getPosition());
        moveAnimalAlongRoute(animal, new String[] {"f", "f", "f", "f", "r", "f", "f", "f", "f"});
        assertEquals(World.WORLD_LOWER_LEFT_CORNER, animal.getPosition());
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
