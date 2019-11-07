package agh.cs.lab2Home;

public class ConsoleMapVisualizer extends MapVisualizer {
    public ConsoleMapVisualizer(IWorldMap map) {
        super(map);
    }

    @Override
    public void display(Vector2d lowerLeft, Vector2d upperRight) {
        String mapStringRepresentation = draw(lowerLeft, upperRight);
        System.out.println(mapStringRepresentation);
    }
}
