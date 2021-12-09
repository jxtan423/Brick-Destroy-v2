package test;

import javax.swing.*;
import java.net.URL;

public class GIF {
    private final JFrame frame;

    public GIF() {
        frame = new JFrame("SIUUUUUUUUUUUUUU");
        URL img = this.getClass().getResource("RonaldoCelebration.gif");
        ImageIcon ii = new ImageIcon(img);
        JLabel label = new JLabel(ii);
        frame.getContentPane().add(label);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocation(510, 338);
    }

    public JFrame getGIFFrame() {
        return frame;
    }
}
