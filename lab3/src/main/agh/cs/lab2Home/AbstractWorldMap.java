package agh.cs.lab2Home;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals;
    protected final MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        animals = new LinkedList<Animal>();
        mapVisualizer = new ConsoleMapVisualizer(this);
    }

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal) {
        if(objectAt(animal.getPosition()) instanceof Animal) return false;
        return animals.add(animal);
    }

    @Override
    public void run(MoveDirection[] directions) {
        ListIterator<Animal> animalIterator = animals.listIterator();
        for(MoveDirection dir : directions) {
            if(!animalIterator.hasNext()) animalIterator = animals.listIterator();
            animalIterator.next().move(dir);
            mapVisualizer.display(getLowerLeftCorner(), getUpperRightCorner());
        }
    }

    @Override
    public abstract boolean isOccupied(Vector2d position);

    @Override
    public abstract Object objectAt(Vector2d position);

    public String toString() {
        return mapVisualizer.draw(getLowerLeftCorner(), getUpperRightCorner());
    }

    protected abstract Vector2d getUpperRightCorner();
    protected abstract Vector2d getLowerLeftCorner();
}
