package agh.cs.lab2Home;

import java.util.*;

public class RectangularMap extends AbstractWorldMap {
    public final Vector2d MAP_UPPER_RIGHT_CORNER, MAP_LOWER_LEFT_CORNER;


    public RectangularMap(int width, int height) {
        super();
        MAP_LOWER_LEFT_CORNER = new Vector2d(0, 0);
        MAP_UPPER_RIGHT_CORNER = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInWorldRange(position) && !isOccupied(position);
    }

    @Override
    protected Vector2d getUpperRightCorner() {
        return MAP_UPPER_RIGHT_CORNER;
    }

    @Override
    protected Vector2d getLowerLeftCorner() {
        return MAP_LOWER_LEFT_CORNER;
    }

    private boolean isInWorldRange(Vector2d position) {
        return position.precedes(MAP_UPPER_RIGHT_CORNER) &&
                position.follows(MAP_LOWER_LEFT_CORNER);
    }
}
