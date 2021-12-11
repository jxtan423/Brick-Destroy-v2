package App.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This abstract class is to display Image
 * by reading the png file from correct
 * directory
 */

public abstract class Image extends JComponent {

    private BufferedImage img;
    private static final Dimension IMAGE_SIZE = new Dimension(511, 511);

    /**
     * This constructor will be set to focus
     * and the size of the frame is set based on
     * the image size.
     * The compiler will read through the path to
     * eventually read the png file.
     * If there's any improper inputs especially typo or wrong
     * directory, there will be an error handler for that.
     *
     */

    public Image() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        setPreferredSize(IMAGE_SIZE);

        try {
            img = ImageIO.read(new File("src/main/resources/brick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return The image size in terms of width and height
     */

    public Dimension getArea() {
        return IMAGE_SIZE;
    }

    /**
     * @return The BufferedImage
     */

    public BufferedImage img() {
        return img;
    }

    /**
     * This abstract button method is for child class to
     * implement within its class.
     */

    public abstract void button();

    /**
     * This abstract content method is for child class to
     * implement within its class.
     * @throws IOException Improper input from user will be ignored
     */

    public abstract void content() throws IOException;

    /**
     * This method is to draw the image start from
     * (0,0) coordinates.
     *
     * @param g The graphics context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, null);
    }
}
