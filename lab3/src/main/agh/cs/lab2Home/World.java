package agh.cs.lab2Home;

import java.util.Arrays;

public class World {
    public static final Vector2d WORLD_UPPER_RIGHT_CORNER = new Vector2d(4,4);
    public static final Vector2d WORLD_LOWER_LEFT_CORNER = new Vector2d(0,0);

    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);
    }
}
