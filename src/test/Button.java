package test;

import java.awt.*;
import java.util.HashMap;

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

    private void set_coordinate(boolean isLeftRightPosition) {
        HashMap<Boolean, Point> hM = new HashMap<>();
        hM.put(Boolean.TRUE, LeftRightPoint());
        hM.put(Boolean.FALSE, MiddlePoint());
        coordinate = hM.get(isLeftRightPosition);
    }

    private Point LeftRightPoint() {
        int x = (HALF_FRAME - BUTTON_WIDTH) / 2;
        int y = (int) ((this.area.height - BUTTON_HEIGHT) * 0.8);
        return new Point(x,y);
    }

    private Point MiddlePoint() {
        int x = (int) (area.getWidth() - BUTTON_WIDTH) /2;
        int y = (int) ((this.area.height - BUTTON_HEIGHT) * 0.4);
        return new Point(x, y);
    }

    private void set_area() {
        BUTTON_WIDTH = (int) (this.area.getWidth() / 3);
        BUTTON_HEIGHT = (int) (this.area.getWidth() / 12);
        btn_area = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public int getHALF_FRAME() {
        return (int) (this.area.getWidth() / 2);
    }

    private void createRectangle() {
        for (int i = 0; i < amountOfButtons; i++)
            btn[i] = new Rectangle(btn_area);
    }

    private void createPosition(boolean isLeftRightPosition) {
        if(isLeftRightPosition)
            leftRightLocation();
        else
            middleLocation();
    }

    private void middleLocation() {
        for(int i = 0; i < amountOfButtons; i++) {
            btn[i].setLocation(coordinate.x, (int) (coordinate.y * ADD_HEIGHT));
            setADD_HEIGHT();
        }
    }

    private void leftRightLocation() {
        for(int i = 0; i < amountOfButtons; i++ ) {
            btn[i].setLocation(coordinate.x,coordinate.y + NEW_ROW);
            i++;
            btn[i].setLocation(coordinate.x + HALF_FRAME,coordinate.y + NEW_ROW);
            setNEW_ROW();
        }
    }

    private void setADD_HEIGHT() {
        ADD_HEIGHT += 0.45;
    }

    private void setNEW_ROW() {
        NEW_ROW -= 65;
    }

    public Rectangle[] getRect() {
        return btn;
    }

    public Font getFont() {
        return buttonFont;
    }
}