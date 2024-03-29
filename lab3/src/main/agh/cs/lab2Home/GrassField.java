package agh.cs.lab2Home;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final int MAP_SPREAD_RATIO = 10;
    private Map<Vector2d, Grass> grassFields;

    public GrassField(int grassFieldsCount) {
        super();
        grassFields = new HashMap<Vector2d, Grass>();
        placeGrass(grassFieldsCount);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal) ;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grassFields.get(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object animal = super.objectAt(position);

        return animal != null ? animal : grassAt(position);
    }

    @Override
    protected Vector2d getUpperRightCorner() {
        Vector2d upperRightCorner = new Vector2d(0,0);
        for(Animal animal : animals) {
            upperRightCorner = upperRightCorner.upperRight(animal.getPosition());
        }
        for(Vector2d grassPosition : grassFields.keySet()) {
            upperRightCorner = upperRightCorner.upperRight(grassPosition);
        }
        return upperRightCorner;
    }

    @Override
    protected Vector2d getLowerLeftCorner() {
        Vector2d lowerLeftCorner = new Vector2d(100,100);
        for(Animal animal : animals) {
            lowerLeftCorner = lowerLeftCorner.lowerLeft(animal.getPosition());
        }
        for(Vector2d grassPosition : grassFields.keySet()) {
            lowerLeftCorner = lowerLeftCorner.lowerLeft(grassPosition);
        }
        return lowerLeftCorner;
    }

    private Grass grassAt(Vector2d position) {
        return grassFields.get(position);
    }

    private void placeGrass(int numberOfGrassFields) {
        boolean isFirstGrassPlacement = grassFields.size() == 0;
        int grassRange = (int) Math.round(Math.sqrt(numberOfGrassFields*MAP_SPREAD_RATIO));

        for(int i = 0; i < numberOfGrassFields; i++) {
            if(isFirstGrassPlacement) {
                Vector2d emptyPosition = rollGrassPosition(new Vector2d(0,0), new Vector2d(grassRange, grassRange));
                grassFields.put(emptyPosition, new Grass(emptyPosition));
            }
        }
    }

    private Vector2d rollGrassPosition(Vector2d lowerLeftBound, Vector2d upperRightBound) {
        Vector2d position;
        do {
            position = Vector2d.getRandomVectorInRange(lowerLeftBound, upperRightBound);
        } while (objectAt(position) != null);
        return position;
    }
}
