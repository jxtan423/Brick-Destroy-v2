package App.Graphics;

import javax.swing.*;

/**
 * This class is to display the
 * Ronaldo celebration GIF
 * which is the most famous one,
 * SIUUUUUUUUUUUUUUUUUUUUUUUUUU
 * in Manchester United jersey
 * with number 7 behind his jersey.
 */

public class GIF {
    private final JFrame frame;

    /**
     * This constructor creates a new frame
     * to display the GIF by using ImageIcon.
     * This frame doesn't include the frame border
     * and the location of this frame is set where the gif
     * is fit into the frame perfectly.
     */

    public GIF() {
        frame = new JFrame("SIUUUUUUUUUUUUUU");
        JLabel label = new JLabel(new ImageIcon("src/main/resources/RonaldoCelebration.gif"));
        frame.getContentPane().add(label);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocation(510, 338);
    }

    /**
     * @return The GIF frame
     */

    public JFrame getGIFFrame() {
        return frame;
    }
}
