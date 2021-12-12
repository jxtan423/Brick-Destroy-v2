package App.Graphics.Frame.InGame.View;

import App.Graphics.Frame.InGame.DetermineBricks;
import java.awt.*;

/**
 * This class inherits the Brick class and implements the
 * DetermineBricks interface.
 * This class is to create a type of brick with
 * specific colour and strength.
 */

public class GreenBrick extends Brick implements DetermineBricks {

    private static final Color DEF_INNER = new Color(0, 153, 0);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CLAY_STRENGTH = 1;

    /**
     * This constructor calls its
     * parents to create the brick.
     *
     * @param point The coordinates of brick
     * @param size The size of brick
     */

    public GreenBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
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
        return super.brickFace;
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
        return new GreenBrick(point, size);
    }
}
