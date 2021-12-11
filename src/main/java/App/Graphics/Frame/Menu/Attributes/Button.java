package App.Graphics.Frame.Menu.Attributes;

import java.awt.*;
import java.util.HashMap;

/**
 * This class creates button(s) by using an array
 * of Rectangle at specific location.
 */

public class Button {

    private int BUTTON_WIDTH;
    private int BUTTON_HEIGHT;
    private final int HALF_FRAME;
    private final int amountOfButtons;
    private double ADD_HEIGHT;
    private int NEW_ROW;

    private Point coordinate;
    private Dimension btn_area;
    private final Dimension area;
    private final Rectangle[] btn;
    private final Font buttonFont;

    /**
     * This constructor determine the amount of buttons to be created and
     * determine the position from the parameters.
     * It executes the createRectangle and createPosition methods after
     * executing set_area and set_coordinate.
     *
     * @param area The area of the Menu frame
     * @param amountOfButtons The number of buttons that want to be created
     * @param isLeftRightPosition Determine which position for the button to be coordinated
     */

    public Button(Dimension area, int amountOfButtons, boolean isLeftRightPosition) {
        this.area = area;
        this.amountOfButtons = amountOfButtons;
        HALF_FRAME = getHALF_FRAME();
        ADD_HEIGHT = 1;
        NEW_ROW = 0;
        btn = new Rectangle[amountOfButtons];
        set_area();
        set_coordinate(isLeftRightPosition);
        buttonFont = new Font("serif", Font.PLAIN, BUTTON_HEIGHT - 4);
        createRectangle();
        createPosition(isLeftRightPosition);
    }

    /**
     * This methods will identify the coordination of the
     * button by getting the parameter value.
     * If the parameter value is true, then it executes
     * LeftRightPoint method else it executes another
     * method.
     * After that, the coordinate will be initialized after identified.
     *
     * @param isLeftRightPosition Determine which position for the button to be coordinated
     */

    private void set_coordinate(boolean isLeftRightPosition) {
        HashMap<Boolean, Point> hM = new HashMap<>();
        hM.put(Boolean.TRUE, LeftRightPoint());
        hM.put(Boolean.FALSE, MiddlePoint());
        coordinate = hM.get(isLeftRightPosition);
    }

    /**
     * The x coordinate and y coordinate are
     * performed through algorithms to get
     * left position.
     *
     * @return a new coordinates
     */

    private Point LeftRightPoint() {
        int x = (HALF_FRAME - BUTTON_WIDTH) / 2;
        int y = (int) ((this.area.height - BUTTON_HEIGHT) * 0.8);
        return new Point(x, y);
    }

    /**
     * The x coordinate and y coordinate are
     * performed through algorithms to get
     * middle position.
     *
     * @return a new coordinates
     */

    private Point MiddlePoint() {
        int x = (int) (area.getWidth() - BUTTON_WIDTH) / 2;
        int y = (int) ((this.area.height - BUTTON_HEIGHT) * 0.4);
        return new Point(x, y);
    }

    /**
     * The area of the button is calculated
     * through algorithms and it is set
     * through creating Dimension object.
     */

    private void set_area() {
        BUTTON_WIDTH = (int) (this.area.getWidth() / 3);
        BUTTON_HEIGHT = (int) (this.area.getWidth() / 12);
        btn_area = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * @return The position of the half menu
     */

    public int getHALF_FRAME() {
        return (int) (this.area.getWidth() / 2);
    }

    /**
     * Rectangle is created through for loop and stored
     * in an array where the value of amountOfButtons
     * limits the number of rectangle to be created.
     */

    private void createRectangle() {
        for (int i = 0; i < amountOfButtons; i++)
            btn[i] = new Rectangle(btn_area);
    }

    /**
     * This method identify the execution of following
     * method by the boolean value of the parameter.
     *
     * @param isLeftRightPosition Determine which position for the button to be coordinated
     */

    private void createPosition(boolean isLeftRightPosition) {
        if (isLeftRightPosition)
            leftRightLocation();
        else
            middleLocation();
    }

    /**
     * This method will create a spacing between 2 buttons in
     * terms of vertical spacing at middle location one by one.
     * The value of spacing is determined by the setADD_HEIGHT
     * method and spacing will be created every iteration.
     */

    private void middleLocation() {
        for (int i = 0; i < amountOfButtons; i++) {
            btn[i].setLocation(coordinate.x, (int) (coordinate.y * ADD_HEIGHT));
            setADD_HEIGHT();
        }
    }

    /**
     * This method will create a spacing between 2 buttons in
     * terms of vertical spacing at left right location
     * one by one.
     * The value of spacing is determined by the setNEW_ROW
     * method and spacing will be created every iteration.
     */

    private void leftRightLocation() {
        for (int i = 0; i < amountOfButtons; i++) {
            btn[i].setLocation(coordinate.x, coordinate.y + NEW_ROW);
            i++;
            btn[i].setLocation(coordinate.x + HALF_FRAME, coordinate.y + NEW_ROW);
            setNEW_ROW();
        }
    }

    /**
     * 45 is added to the current value everytime
     * this method is invoked.
     */

    private void setADD_HEIGHT() {
        ADD_HEIGHT += 0.45;
    }

    /**
     * 65 is deducted to the current value everytime
     * this method is invoked.
     */
    private void setNEW_ROW() {
        NEW_ROW -= 65;
    }

    /**
     * @return the array of button
     */

    public Rectangle[] getRect() {
        return btn;
    }

    /**
     * @return The font of the button
     */

    public Font getFont() {
        return buttonFont;
    }
}