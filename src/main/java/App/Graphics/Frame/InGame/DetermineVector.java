package App.Graphics.Frame.InGame;

import java.awt.*;
import java.util.Random;

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

    public abstract Point makeRandomPoint(Point from, Point to);
}
