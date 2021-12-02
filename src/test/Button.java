package test;

import java.awt.*;

public class Button {

    private int BUTTON_WIDTH;
    private int BUTTON_HEIGHT;
    private final int HALF_FRAME;
    private int Button_X;
    private int Button_Y;

    private final Dimension area;
    private final Rectangle[] btn;
    private final Font buttonFont;

    public Button(Dimension area, int amountOfButtons, boolean isLeftRightPosition) {
        this.area = area;
        HALF_FRAME = (int) (this.area.getWidth() / 2);
        btn = new Rectangle[amountOfButtons];
        setWidth();
        setHeight();
        setButton_X(isLeftRightPosition);
        setButton_Y(isLeftRightPosition);
        buttonFont = new Font("serif", Font.PLAIN, BUTTON_HEIGHT - 4);
        createRectangle(btn, amountOfButtons, isLeftRightPosition);
    }

    private void createRectangle(Rectangle[] btn, int amountOfButtons, boolean isLeftRightPosition) {
        int i;
        for (i = 0; i < amountOfButtons; i++) {
            btn[i] = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        if(!isLeftRightPosition) {
            int ADD_HEIGHT = 0;
            for(i = 0; i < amountOfButtons; i++) {
                btn[i].setLocation(Button_X, Button_Y + ADD_HEIGHT);
                ADD_HEIGHT += 56;
            }
        }
        else {
            int NEW_ROW = 0;
            for(i = 0; i < amountOfButtons; i++ ) {
                btn[i].setLocation(Button_X,Button_Y + NEW_ROW);
                i++;
                btn[i].setLocation(Button_X + HALF_FRAME,Button_Y + NEW_ROW);
                NEW_ROW -= 65;
            }
        }
    }

    public void setWidth() {
        this.BUTTON_WIDTH = (int) (this.area.getWidth() / 3);
    }
    public void setHeight() {
        this.BUTTON_HEIGHT = (int) (this.area.getWidth() / 12);
    }

    public void setButton_X(boolean isLeftRightPosition) {
        if(isLeftRightPosition) {
            int SPACE_LEFT = (this.HALF_FRAME - BUTTON_WIDTH);
            Button_X = SPACE_LEFT / 2;
        }
        else
            Button_X = (int) ((area.getWidth() - BUTTON_WIDTH) /2);
    }

    public void setButton_Y(boolean isLeftRightButton) {
        if(isLeftRightButton)
            Button_Y = (int) ((this.area.height - BUTTON_HEIGHT) * 0.8);
        else
            Button_Y = 150;
    }

    public Rectangle[] getRect() {
        return btn;
    }
    public Font getFont() {
        return buttonFont;
    }
}