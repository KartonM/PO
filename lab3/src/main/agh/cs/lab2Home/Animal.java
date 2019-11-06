package agh.cs.lab2Home;

public class Animal {
    private MapDirection facedDirection;
    private Vector2d position;
    private IWorldMap worldMap;

    public Animal(IWorldMap map) {
        worldMap = map;
        facedDirection = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map);
        position = initialPosition;
    }

    public MapDirection getFacedDirection() {
        return facedDirection;
    }
    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return facedDirection.name().substring(0,1);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD:
                tryMove(facedDirection.toUnitVector());
                break;
            case BACKWARD:
                tryMove(facedDirection.toUnitVector().opposite());
                break;
            case RIGHT:
                facedDirection = facedDirection.next();
                break;
            case LEFT:
                facedDirection = facedDirection.previous();
                break;
        }
    }

    private void tryMove(Vector2d moveVector) {
        Vector2d newPosition = position.add(moveVector);
        if(worldMap.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }
}
