package App.Graphics.Frame.Menu.Attributes;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

/**
 * This class is to create text for
 * non-button text and button text
 * along with coordinates.
 */

public class Text {

    private static final double HEIGHT_DIFFERENCE = 1.1;

    private Point coordinate;
    private final Dimension area;
    private final Rectangle button;
    private final Rectangle2D textRect;

    /**
     * This constructor will initialize the variables from parameters.
     * createText will be executed from this constructor.
     *
     * @param button The Rectangle button that will be displayed on screen
     * @param textRect The text Rectangle that includes text
     * @param area The area of the menu frame
     * @param isButtonText Identify whether is a non-button text or button text
     */

    public Text(Rectangle button, Rectangle2D textRect, Dimension area, boolean isButtonText) {
        this.button = button;
        this.textRect = textRect;
        this.area = area;
        createText(isButtonText);
    }

    /**
     * This methods is to identify the button text or non-button text.
     * If the boolean value is true, then it executes the button_text
     * method else it executes another.
     * After that, the coordinate will be initialized after identified.
     *
     * @param isButton Identify whether is a non-button text or button text
     */

    private void createText(boolean isButton) {
        HashMap<Boolean, Point> hM = new HashMap<>();
        hM.put(Boolean.TRUE, button_text());
        hM.put(Boolean.FALSE, text());
        coordinate = hM.get(isButton);
    }

    /**
     * The int x will execute a method and
     * same goes to int y to get int values.
     * After that, both x and y will be initialized
     * as a new coordinates for button text.
     *
     * @return a new coordinates
     */

    private Point button_text() {
        int x = setButton_Text_X();
        int y = setButton_Text_Y();
        return new Point(x, y);
    }

    /**
     * The int x will execute a method and
     * same goes to int y to get int values.
     * After that, both x and y will be initialized
     * as a new coordinates for non-button text.
     *
     * @return a new coordinates
     */

    private Point text() {
        int x = setText_X();
        int y = setText_Y();
        return new Point(x, y);
    }

    /**
     * This method performs algorithms
     * to get the value of x coordinate
     * button text.
     *
     * @return the value of x coordinate
     */

    public int setButton_Text_X() {
        int SPACE_LEFT = (int) ((this.button.getWidth() - this.textRect.getWidth()) / 2);
        return SPACE_LEFT + this.button.x;
    }

    /**
     * This method performs algorithms
     * to get the value of y coordinate
     * button text.
     *
     * @return the value of y coordinate
     */

    public int setButton_Text_Y() {
        int SPACE_LEFT = (int) ((this.button.getHeight() - this.textRect.getHeight()) / 2);
        int TEXT = (int) (this.button.y + (button.height * 0.9));
        return SPACE_LEFT + TEXT;
    }

    /**
     * This method performs algorithms
     * to get the value of x coordinate
     * non-button text.
     *
     * @return the value of x coordinate
     */

    public int setText_X() {
        return (int) (this.area.getWidth() - this.textRect.getWidth()) / 2;
    }

    /**
     * This method performs algorithms
     * to get the value of y coordinate
     * non-button text.
     *
     * @return the value of y coordinate
     */

    public int setText_Y() {
        return (int) (area.getHeight() / 2);
    }

    /**
     * @return the coordinate for either button text or non-button text
     */

    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * This method assigned a new value for y coordinate
     * by performing the algorithms.
     *
     * @param y the y coordinate for the previous button
     */

    public void setNewText_Y(int y) {
        this.coordinate.y = y + (int) (this.textRect.getHeight() * HEIGHT_DIFFERENCE);
    }
}
