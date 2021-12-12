package App.Graphics.Frame.InGame;

import java.awt.*;
import java.util.Random;

/**
 * This enum class is to identify whether
 * the crack is in the form of horizontal
 * or vertical based on the impact direction
 * from the ball.
 */

public enum DetermineVector {

    HORIZONTAL {
        @Override
        public Point makeRandomPoint(Point from, Point to) {
            pos = rnd.nextInt(to.x - from.x) + from.x;
            return new Point(pos, to.y);
        }
    },
    VERTICAL {
        @Override
        public Point makeRandomPoint(Point from, Point to) {
            pos = rnd.nextInt(to.y - from.y) + from.y;
            return new Point(to.x, pos);
        }
    };

    int pos;
    Random rnd = new Random();

    /**
     * This abstract method is to implement on every
     * enum constant to perform similar method but
     * different functionality.
     *
     * @param from The start point of the crack
     * @param to The end point of the crack
     * @return The crack coordinate where it forms
     */

    public abstract Point makeRandomPoint(Point from, Point to);
}
