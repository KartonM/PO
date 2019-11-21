package agh.cs.lab2Home;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals;
    protected Map<Vector2d, Animal> animalsOnPositions;
    protected final MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        animals = new LinkedList<Animal>();
        mapVisualizer = new ConsoleMapVisualizer(this);
        animalsOnPositions = new HashMap<Vector2d, Animal>();
    }

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal) {
        if(objectAt(animal.getPosition()) instanceof Animal)
            throw new IllegalArgumentException(animal.getPosition() + " is already occupied by another animal on this map.");
        animalsOnPositions.put(animal.getPosition(), animal);
        return animals.add(animal);
    }

    @Override
    public void run(MoveDirection[] directions) {
        for(int i = 0; i < directions.length; i++) {
            Animal animal = animals.get(i % animals.size());

            animalsOnPositions.remove(animal.getPosition());
            animal.move(directions[i]);
            animalsOnPositions.put(animal.getPosition(), animal);

            mapVisualizer.display(getLowerLeftCorner(), getUpperRightCorner());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalsOnPositions.get(position);
    }

    public String toString() {
        return mapVisualizer.draw(getLowerLeftCorner(), getUpperRightCorner());
    }

    protected abstract Vector2d getUpperRightCorner();
    protected abstract Vector2d getLowerLeftCorner();
}
