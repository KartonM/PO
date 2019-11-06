package agh.cs.lab2Home;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

public class OptionParserTest {
    @Test
    public void ParserTest() {
        String[] args = new String[] {"f", "b", "l", "r", "asd", "asd", "forward", "backward", "left", "right"};
        MoveDirection[] expected = new MoveDirection[] {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        MoveDirection[] parsed = OptionsParser.parse(args);
        //System.out.println(Arrays.toString(parsed));
        assertTrue(Arrays.equals(expected, parsed));
    }
}
