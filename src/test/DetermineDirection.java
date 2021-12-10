package test;

import java.awt.*;

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

    public abstract Point makeCrack(Crack crack, Rectangle bounds);
}
