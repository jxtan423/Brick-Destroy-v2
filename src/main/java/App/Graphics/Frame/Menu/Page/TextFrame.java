package App.Graphics.Frame.Menu.Page;

import App.Graphics.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.image.RescaleOp;
import java.io.IOException;

/**
 * This abstract class is to
 * display the image with "Back" button.
 */

public abstract class TextFrame extends Image {

    private JFrame frame;
    private JButton btn;
    private Point coordinate;
    public final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Get image from parent class.
     * Set the frame for this class.
     * Execute button method in order
     * to show the "Back" button.
     * @param name The name of the frame
     */

    public TextFrame(String name) {
        super();
        setFrame(name);
        button();
    }

    /**
     * Set the frame in terms of size, transparency,
     * location, layout and include the image.
     * @param name The name of the frame
     */

    public void setFrame(String name) {
        RescaleOp op = new RescaleOp(.6f, 0, null);
        frame = new JFrame(name);
        frame.setSize(super.getArea());
        frame.setUndecorated(true);
        setCoordinate();
        frame.setLocation(coordinate);
        frame.setLayout(null);
        frame.setContentPane(new JLabel(new ImageIcon(op.filter(super.img(), null))));
    }

    /**
     * Set the location of frame with the formula
     * in order to set it center of the user's screen.
     */
    private void setCoordinate() {
        int x = (screenSize.width / 2) - (frame.getWidth() / 2);
        int y = (screenSize.height / 2) - (frame.getHeight() / 2);
        this.coordinate = new Point(x, y);
    }

    /**
     * @return The "Back" button in terms of size, coordinates and text
     */

    public JButton getBtn() {
        return btn;
    }

    /**
     * @return The TextFrame frame in terms of size and coordinates.
     */

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Create JButton in terms of size, positioning, font
     * and add wording for the button.
     * Action listener is added to the JButton.
     * The JButton is added into the frame.
     */
    @Override
    public void button() {
        btn = new JButton("Back");
        btn.setFont(new Font("Arial", Font.ITALIC, 20));
        btn.setBounds(400, 470, 100, 30);
        frame.add(btn);
    }

    /**
     * This is abstract class for children classes to implement
     * their respective content in their own frame.
     * @throws IOException improper data is ignored
     */
    @Override
    public abstract void content() throws IOException;
}