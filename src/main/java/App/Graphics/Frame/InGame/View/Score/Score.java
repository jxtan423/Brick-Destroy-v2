package App.Graphics.Frame.InGame.View.Score;

import App.Graphics.Frame.Menu.Page.TextFrame;
import javax.swing.*;
import java.awt.*;

/**
 * This abstract class is to create a small frame
 * A.K.A a pop up frame whenever the user completes
 * the level.
 */

public abstract class Score extends TextFrame {

    private final JFrame frame;
    private final JButton btn;

    private final Dimension screenSize = super.screenSize;

    /**
     * This constructor will call its parents
     * to get the attributes and assigned the
     * frame and button from parents class.
     * Frame reformat and button reformat will
     * be executed.
     *
     * @param name The name of the frame
     * @param buttonText The text for the button
     */

    public Score(String name, String buttonText) {
        super(name);
        frame = super.getFrame();
        btn = super.getBtn();
        frame.setContentPane(new JLabel());
        frameReformat();
        buttonReformat(buttonText);
    }

    /**
     * Format the size of the frame by making it
     * into smaller since parents' frame has bigger
     * frame in terms of the size.
     * A new coordinate for the new frame is coordinated
     * and set perfectly on user's screen.
     */

    public void frameReformat() {
        Dimension size = new Dimension(250, 100);
        frame.setSize(size);
        Point FRAME_COORDINATES = new Point(FRAME_X(), FRAME_Y());
        frame.setLocation(FRAME_COORDINATES);
    }

    /**
     * The button is coordinated and resize with
     * specific fonts.
     * The button is added with text and is added
     * into the frame.
     *
     * @param buttonText The text for the button
     */

    public void buttonReformat(String buttonText) {
        Rectangle BTN = new Rectangle(75, 65, 100, 30);
        btn.setFont(new Font("Arial", Font.ITALIC, 16));
        btn.setText(buttonText);
        btn.setBounds(BTN);
        frame.add(btn);
    }

    /**
     * @return The x coordinate of the frame
     */

    private int FRAME_X() {
        return (screenSize.width / 2) - (frame.getWidth() / 2);
    }

    /**
     * @return The y coordinate of the frame
     */

    private int FRAME_Y() {
        return screenSize.height / 2;
    }

    /**
     * Does nothing in this class
     * and will be implemented on its
     * child class.
     */

    @Override
    public abstract void content();
}
