package App.Graphics.Frame.InGame.View.Brick;

import App.Graphics.Frame.InGame.Model.Cracking.Crack;
import App.Graphics.Frame.InGame.Model.GameWall.DetermineBricks;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This class inherits the Brick class and implements the
 * DetermineBricks interface.
 * This class is to create a type of brick with
 * specific colour and strength.
 */

public class CementBrick extends Brick implements DetermineBricks {

    public int MIN_CRACK = 1;
    public int DEF_CRACK_DEPTH = 1;
    public int DEF_STEPS = 35;

    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private final Crack crack;
    private Shape brickFace;

    /**
     * This constructor calls its
     * parents to create the brick.
     * It creates the crack object.
     * It gets the brickFace value from
     * the parents and initialized it.
     *
     * @param point The coordinates of brick
     * @param size The size of brick
     */

    public CementBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, CEMENT_STRENGTH);
        crack = new Crack(this, DEF_CRACK_DEPTH, DEF_STEPS);
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
     * This method is to identify whether the brick has
     * a crack or not.
     * If yes, if there's anymore further impact, the
     * brick will break.
     *
     * @param point The ball direction
     * @param dir The crack position
     * @return The boolean value on whether the crack is
     * created or not before collision and after collision
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if (super.isBroken())
            return false;
        super.impact();
        if (!super.isBroken()) {
            crack.makeCrack(point, dir);
            updateBrick();
            return false;
        }
        return true;
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
     * This method is to update
     * the crack state of the brick.
     */
    private void updateBrick() {
        if (!super.isBroken()) {
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(), false);
            brickFace = gp;
        }
    }

    /**
     * This method is to assigned the
     * default strength of the
     * brick which get from the
     * parameters in the early stage
     * to reset the brick back to normal,
     * which is not broken.
     * The crack.reset() method will be
     * executed too which mean the crack will
     * be reset which get rid of the crack.
     * The brickFace is assigned once again to
     * get the initial brickFace.
     */

    public void repair() {
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
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
        return new CementBrick(point, size);
    }
}
