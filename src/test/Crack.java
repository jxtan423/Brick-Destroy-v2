package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class Crack {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private final Brick brick;
    private final GeneralPath crack;

    private final int crackDepth;
    private final int steps;

    public static Point start = new Point();
    public static Point end = new Point();

    public Crack(Brick brick, int crackDepth, int steps) {
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }

    public GeneralPath draw() {
        return crack;
    }

    public void reset() {
        crack.reset();
    }

    protected void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.brickFace.getBounds();
        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point tmp = DetermineDirection.values()[direction].makeCrack(this, bounds);
        makeCrack(impact, tmp);
    }

    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();
        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);
        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return Brick.rnd.nextInt(n) - bound;
    }

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);
        return (i > low) && (i < up);
    }

    private int jumps(int bound, double probability) {
        if (Brick.rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

    public static Point makeRandomPoint(Point from, Point to, int direction) {
        return DetermineVector.values()[direction].makeRandomPoint(from, to);
    }
}
