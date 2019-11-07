package agh.cs.lab2Home;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class StringDimensions {
    public final int linesCount;
    public final int maxLineLength;

    StringDimensions(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        this.linesCount = lines.length;
        this.maxLineLength = Arrays.stream(lines).mapToInt(s -> s.length()).max().orElseThrow(NoSuchElementException::new);
    }
}
