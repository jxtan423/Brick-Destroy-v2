package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Image extends JComponent {

    private BufferedImage img;
    private static final Dimension IMAGE_SIZE = new Dimension(511, 511);

    public Image() {
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

    public BufferedImage img() {
        return img;
    }

    public abstract void button();

    public abstract void content() throws IOException;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, null);
    }
}
