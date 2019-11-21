package agh.cs.lab2Home;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
//        IWorldMap map = new RectangularMap(10, 5);
        IWorldMap map = new GrassField(10);

        try {
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3,4)));
            map.run(directions);
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }

    }
}
