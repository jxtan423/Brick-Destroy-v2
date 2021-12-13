package App.Graphics.Frame.InGame.View.Brick;

import App.Graphics.Frame.InGame.Model.GameWall.DetermineBricks;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class inherits the Brick class and implements the
 * DetermineBricks interface.
 * This class is to create a type of brick with
 * specific colour and strength.
 */

public class SteelBrick extends Brick implements DetermineBricks {

    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private final Random rnd;
    private final Shape brickFace;

    /**
     * This constructor calls its
     * parents to create the brick.
     * Random number generator is instantiated.
     * brickFace is assigned by its parents'
     * brickFace.
     *
     * @param point The coordinates of brick
     * @param size The size of brick
     */

    public SteelBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
    }

    /**
     * This method creates a rectangle object and
     * return back the rectangle along with
     * specific size and coordinates.
     *
     * @param pos The coordinates of brick
     * @param size The size of brick
     * @return The shape of the brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    /**
     * This method is to get the
     * shape of the brick by getting
     * from its parents.
     *
     * @return The shape of the brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * This method is to setImpact to the
     * brick whenever the ball collides
     * with the brick.
     * The impact method will be executed
     * if the brick is not broken.
     *
     * @param point The ball direction
     * @param dir The crack position
     * @return Boolean value for the state of the respective brick
     */

    public boolean setImpact(Point2D point, int dir) {
        if (super.isBroken())
            return false;
        impact();
        return super.isBroken();
    }

    /**
     * This method is to identify if the
     * random number is smaller than the
     * steel probability, it will execute
     * the impact method at parent class.
     */

    public void impact() {
        if (rnd.nextDouble() < STEEL_PROBABILITY) {
            super.impact();
        }
    }

    /**
     * This object is created and it is returned
     * in the specific type of brick.
     *
     * @param point The coordinates of brick
     * @param size The size of brick
     * @return The respective type of brick object
     */
    @Override
    public Brick getSpecificBrick(Point point, Dimension size) {
        return new SteelBrick(point, size);
    }
}
