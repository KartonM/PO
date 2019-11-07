package agh.cs.lab2Home;

import java.util.*;

public class RectangularMap implements IWorldMap {
    public final Vector2d MAP_UPPER_RIGHT_CORNER, MAP_LOWER_LEFT_CORNER;
    private List<Animal> animals;
    private final MapVisualizer mapVisualizer;


    public RectangularMap(int width, int height) {
        MAP_LOWER_LEFT_CORNER = new Vector2d(0, 0);
        MAP_UPPER_RIGHT_CORNER = new Vector2d(width - 1, height - 1);
        animals = new LinkedList<Animal>();
        mapVisualizer = new SwingMapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInWorldRange(position) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(isOccupied(animal.getPosition())) return false;
        return animals.add(animal);
    }

    @Override
    public void run(MoveDirection[] directions) {
        ListIterator<Animal> animalIterator = animals.listIterator();
        for(MoveDirection dir : directions) {
            mapVisualizer.display(MAP_LOWER_LEFT_CORNER, MAP_UPPER_RIGHT_CORNER);
            if(!animalIterator.hasNext()) animalIterator = animals.listIterator();
            animalIterator.next().move(dir);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.stream().anyMatch(a -> a.getPosition().equals(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.stream().filter(a -> a.getPosition().equals(position)).findFirst().orElse(null);
    }

    public String toString() {
        return mapVisualizer.draw(MAP_LOWER_LEFT_CORNER, MAP_UPPER_RIGHT_CORNER);
    }

    private boolean isInWorldRange(Vector2d position) {
        return position.precedes(MAP_UPPER_RIGHT_CORNER) &&
                position.follows(MAP_LOWER_LEFT_CORNER);
    }
}
