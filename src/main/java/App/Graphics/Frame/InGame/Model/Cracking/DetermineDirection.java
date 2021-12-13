package App.Graphics.Frame.InGame.Model.Cracking;

import java.awt.*;

/**
 * This enum class is to identify the
 * direction of the ball comes from
 * in order to create a crack
 * from specific start point to the
 * end point.
 */

public enum DetermineDirection {

    LEFT {
        @Override
        public Point makeCrack(Crack crack, Rectangle bounds) {
            Crack.start.setLocation(bounds.x + bounds.width, bounds.y);
            Crack.end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            return Crack.makeRandomPoint(Crack.start, Crack.end, Crack.VERTICAL);
        }
    },
    RIGHT {
        @Override
        public Point makeCrack(Crack crack, Rectangle bounds) {
            Crack.start.setLocation(bounds.getLocation());
            Crack.end.setLocation(bounds.x, bounds.y + bounds.height);
            return Crack.makeRandomPoint(Crack.start, Crack.end, Crack.VERTICAL);
        }
    },
    UP {
        @Override
        public Point makeCrack(Crack crack, Rectangle bounds) {
            Crack.start.setLocation(bounds.x, bounds.y + bounds.height);
            Crack.end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            return Crack.makeRandomPoint(Crack.start, Crack.end, Crack.HORIZONTAL);
        }
    },
    DOWN {
        @Override
        public Point makeCrack(Crack crack, Rectangle bounds) {
            Crack.start.setLocation(bounds.getLocation());
            Crack.end.setLocation(bounds.x + bounds.width, bounds.y);
            return Crack.makeRandomPoint(Crack.start, Crack.end, Crack.HORIZONTAL);
        }
    };

    /**
     * This abstract method is to implement on every
     * enum constant to perform similar method where crack
     * is created and formed but different functionality
     * in terms of positioning and direction of the cracks.
     *
     * @param crack Crack class
     * @param bounds The details of the brick in terms size, coordinate and location
     * @return The crack coordinates where it forms
     */

    public abstract Point makeCrack(Crack crack, Rectangle bounds);
}
