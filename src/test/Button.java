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

    public Button(Dimension area, int amountOfButtons) {

        this.area = area;
        HALF_FRAME = (int) (this.area.getWidth() / 2);

        setBUTTON_WIDTH();
        setBUTTON_HEIGHT();
        setButton_X();
        setButton_Y();

        buttonFont = new Font("Monospaced", Font.PLAIN, getBUTTON_HEIGHT() - 3);

        btn = new Rectangle[amountOfButtons];
        createRectangle(btn, amountOfButtons);
    }

    private void createRectangle(Rectangle[] btn, int amountOfButtons) {
        for (int i = 0; i < amountOfButtons; i++) {
            btn[i] = new Rectangle(getBUTTON_WIDTH(), getBUTTON_HEIGHT());
        }
    }

    public int getBUTTON_WIDTH() {
        return BUTTON_WIDTH;
    }

    public int getBUTTON_HEIGHT() {
        return BUTTON_HEIGHT;
    }

    public void setBUTTON_WIDTH() {
        this.BUTTON_WIDTH = (int) (this.area.getWidth() / 3);
    }

    public void setBUTTON_HEIGHT() {
        this.BUTTON_HEIGHT = (int) (this.area.getWidth() / 12);
    }

    public Rectangle[] getRect() {
        return btn;
    }

    public Font getFont() {
        return buttonFont;
    }

    public void setButton_X() {
        int SPACE_LEFT = (this.HALF_FRAME - this.getBUTTON_WIDTH());
        Button_X = SPACE_LEFT / 2;
    }

    public int getButton_X(boolean isLeftButton) {
        if(isLeftButton)
            return this.Button_X;
        else
            return this.HALF_FRAME + this.Button_X;
    }

    public void setButton_Y() {
        Button_Y = (int) ((this.area.height - this.getBUTTON_HEIGHT()) * 0.8);
    }

    public int getButton_Y(boolean isFirstRow) {
        int FIRST_ROW = 65;
        if(isFirstRow)
            return this.Button_Y - FIRST_ROW;
        else
            return this.Button_Y;
    }
}