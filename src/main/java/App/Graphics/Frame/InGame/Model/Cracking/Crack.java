package App.Graphics.Frame.InGame.Model.Cracking;

import App.Graphics.Frame.InGame.View.Brick.Brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is to create a crack
 * on brick whenever the ball hits
 * the respective brick.
 */

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

    private final Random rnd;

    public static Point start = new Point();
    public static Point end = new Point();

    /**
     * This constructor is to assigned
     * the variables based on the value
     * of the parameters.
     * GeneralPath is instantiated and is
     * assigned to a variable.
     *
     * @param brick The respective brick
     * @param crackDepth The depth of the crack
     * @param steps The steps of the crack
     */

    public Crack(Brick brick, int crackDepth, int steps) {
        rnd = new Random();
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }

    /**
     * This method is to draw
     * the crack on brick.
     *
     * @return the GeneralPath of the crack
     */

    public GeneralPath draw() {
        return crack;
    }

    /**
     * This method is to reset
     * the crack on brick
     */

    public void reset() {
        crack.reset();
    }

    /**
     * This method is to set the coordinates
     * of the crack based on brickFace.
     * Enum class will be instantiated to
     * identify the impact direction from the ball
     * to create an exact crack position.
     * The makeCrack method is executed after.
     *
     * @param point The ball direction
     * @param direction The crack position
     */

    public void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.getBrickFace().getBounds();
        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point tmp = DetermineDirection.values()[direction].makeCrack(this, bounds);
        makeCrack(impact, tmp);
    }

    /**
     * This method is the detail of how
     * crack is formed in terms of
     * calculation and algorithms.
     * The crack will link together
     * becoming a line of crack.
     *
     * @param start The start point of the crack
     * @param end The end point of the crack
     */

    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();
        path.moveTo(start.x, start.y);

        double w = calculate_width(start, end);
        double h = calculate_height(start, end);

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

    /**
     * This method is to identify the bound
     * along with the random number
     * generator.
     *
     * @param bound The bound
     * @return The number of bound
     */

    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    /**
     * This method is to calculate
     * the width of the crack with
     * some calculation and algorithms.
     *
     * @param start The start point of the crack
     * @param end The end point of the crack
     * @return The width of the crack
     */

    private double calculate_width(Point start, Point end) {
        return (end.x - start.x) / (double) steps;
    }

    /**
     * This method is to calculate
     * the height of the crack with
     * some calculation and algorithms.
     *
     * @param start The start point of the crack
     * @param end The end point of the crack
     * @return The height of the crack
     */

    private double calculate_height(Point start, Point end) {
        return (end.y - start.y) / (double) steps;
    }

    /**
     * This method is to identify whether the
     * crack can be formed in the middle of
     * the brick following some calculation
     * and algorithms.
     *
     * @param i The current index of the crack point
     * @param steps The steps of the crack
     * @param divisions The crack division
     * @return Boolean value on whether the crack can be formed in the middle or not
     */

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);
        return (i > low) && (i < up);
    }

    /**
     * This method is to identify whether there
     * will be a bound or not depending on
     * probability.
     *
     * @param bound The bound
     * @param probability The probability
     * @return The number of bounds
     */

    private int jumps(int bound, double probability) {
        if (rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

    /**
     * This method will instantiate the
     * DetermineVector enum class to
     * determine the exact coordinate
     * of the crack should be formed.
     *
     * @param from The start point of the crack
     * @param to The end point of the crack
     * @param direction The vector of the crack
     * @return The point of the crack
     */

    public static Point makeRandomPoint(Point from, Point to, int direction) {
        return DetermineVector.values()[direction].makeRandomPoint(from, to);
    }
}