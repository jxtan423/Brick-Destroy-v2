package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DisplayImage extends JComponent {

    private BufferedImage img;
    private static final Dimension IMAGE_SIZE = new Dimension(511, 511);

    public DisplayImage() {

        this.setFocusable(true);
        this.requestFocusInWindow();

        setPreferredSize(IMAGE_SIZE);

        try {
            img = ImageIO.read(getClass().getResourceAsStream("brick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dimension getArea() {
        return IMAGE_SIZE;
    }

    public Image img() {
        return img;
    }

}
