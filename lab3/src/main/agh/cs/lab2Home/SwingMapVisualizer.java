package agh.cs.lab2Home;

import javax.swing.*;
import java.awt.*;

public class SwingMapVisualizer extends MapVisualizer {
    private final int FONT_SIZE = 24;
    private final long ANIMATION_FRAME_DURATION = 500;

    private JFrame frame = null;
    private JTextArea textArea = null;

    public SwingMapVisualizer(IWorldMap map) {
        super(map);
    }

    @Override
    public void display(Vector2d lowerLeft, Vector2d upperRight) {
        String mapStringRepresentation = draw(lowerLeft, upperRight);

        if(frame == null) {
            initializeJFrame(new StringDimensions(mapStringRepresentation));
        }
        textArea.setText(mapStringRepresentation);
        textArea.update(textArea.getGraphics());

        try {
            Thread.sleep(ANIMATION_FRAME_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initializeJFrame(StringDimensions dim) {
        frame = new JFrame();
        textArea = new JTextArea("Loading...", dim.maxLineLength, dim.linesCount);
        int windowWidth = dim.maxLineLength *FONT_SIZE; //*3/4;
        int windowHeight = dim.maxLineLength *FONT_SIZE;///2;


        textArea.setBounds(0, 0, windowWidth, windowHeight);
        textArea.setFont(new Font("Consolas", Font.PLAIN, FONT_SIZE));

        frame.add(textArea);

        frame.setSize(windowWidth, windowHeight);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
