package test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class Text {

    private static final double HEIGHT_DIFFERENCE = 1.1;

    private Point coordinate;
    private final Dimension area;
    private final Rectangle button;
    private final Rectangle2D textRect;

    public Text(Rectangle button, Rectangle2D textRect, Dimension area, boolean isButtonText) {
        this.button = button;
        this.textRect = textRect;
        this.area = area;
        createText(isButtonText);
    }

    private void createText(boolean isButton) {
        HashMap<Boolean, Point> hM = new HashMap<>();
        hM.put(Boolean.TRUE, button_text());
        hM.put(Boolean.FALSE, text());
        coordinate = hM.get(isButton);
    }

    private Point button_text() {
        int x = setButton_Text_X();
        int y = setButton_Text_Y();
        return new Point(x, y);
    }

    private Point text() {
        int x = setText_X();
        int y = setText_Y();
        return new Point(x, y);
    }

    public int setButton_Text_X() {
        int SPACE_LEFT = (int) ((this.button.getWidth() - this.textRect.getWidth()) / 2);
        return SPACE_LEFT + this.button.x;
    }

    public int setButton_Text_Y() {
        int SPACE_LEFT = (int) ((this.button.getHeight() - this.textRect.getHeight()) / 2);
        int TEXT = (int) (this.button.y + (button.height * 0.9));
        return SPACE_LEFT + TEXT;
    }

    public int setText_X() {
        return (int) (this.area.getWidth() - this.textRect.getWidth()) / 2;
    }

    public int setText_Y() {
        return (int) (area.getHeight() / 2);
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setNewText_Y(int y) {
        this.coordinate.y = y + (int) (this.textRect.getHeight() * HEIGHT_DIFFERENCE);
    }
}
