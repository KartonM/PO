package agh.cs.lab2Home;

import java.util.Arrays;
import java.util.stream.Stream;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        return Arrays.stream(args).map(s -> argToMapDirection(s))
                                  .filter(s -> s != null)
                                  .toArray(MoveDirection[]::new);
    }

    private static MoveDirection argToMapDirection(String s) {
        if(s.equals("f") || s.equals("forward")) return MoveDirection.FORWARD;
        if(s.equals("b") || s.equals("backward")) return MoveDirection.BACKWARD;
        if(s.equals("l") || s.equals("left")) return MoveDirection.LEFT;
        if(s.equals("r") || s.equals("right")) return MoveDirection.RIGHT;

        throw new IllegalArgumentException(s + " is not legal move specification");
    }
}
