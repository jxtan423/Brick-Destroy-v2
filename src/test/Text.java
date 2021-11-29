package test;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Text {

    private static final double HEIGHT_DIFFERENCE = 1.1;

    private int Button_Text_X;
    private int Button_Text_Y;
    private int Text_X;
    private int Text_Y;

    private final Dimension area;
    private final Rectangle button;
    private final Rectangle2D textRect;

    public Text(Rectangle button, Rectangle2D textRect, Dimension area, boolean isButtonText) {
        this.button = button;
        this.textRect = textRect;
        this.area = area;
        if (isButtonText) {
            setButton_Text_X();
            setButton_Text_Y();
        } else {
            setText_X();
            setText_Y();
        }
    }

    public int getButton_Text_X() {
        return this.Button_Text_X;
    }

    public void setButton_Text_X() {
        int SPACE_LEFT = (int) ((this.button.getWidth() - this.textRect.getWidth()) / 2);
        Button_Text_X = SPACE_LEFT + this.button.x;
    }

    public int getButton_Text_Y() {
        return this.Button_Text_Y;
    }

    public void setButton_Text_Y() {
        int SPACE_LEFT = (int) ((this.button.getHeight() - this.textRect.getHeight()) / 2);
        int TEXT = (int) (this.button.y + (button.height * 0.9));
        Button_Text_Y = SPACE_LEFT + TEXT;
    }

    public int getText_X() {
        return this.Text_X;
    }

    public void setText_X() {
        Text_X = (int) (this.area.getWidth() - this.textRect.getWidth()) / 2;
    }

    public int getText_Y() {
        return this.Text_Y;
    }

    public void setText_Y() {
        Text_Y = (int) (area.getHeight() / 2);
    }

    public void setNewText_Y(int y) {
        this.Text_Y = y + (int) (this.textRect.getHeight() * HEIGHT_DIFFERENCE);
    }
}
