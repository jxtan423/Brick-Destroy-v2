package App.Graphics.Frame.InGame.View.Brick;

import App.Graphics.Frame.InGame.Model.GameWall.DetermineBricks;

import java.awt.*;
import java.util.HashMap;

/**
 * This class inherits the Brick class and implements the
 * DetermineBricks interface.
 * This class is to create a type of brick with
 * specific colour and strength.
 */

public class RedBrick extends Brick implements DetermineBricks {

    private static final Color DEF_INNER = new Color(204, 0, 0);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STRENGTH = 3;

    /**
     * This constructor calls its
     * parents to create the brick.
     *
     * @param point The coordinates of brick
     * @param size The size of brick
     */

    public RedBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, STRENGTH);
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
        return super.getBrickFace();
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
        return new RedBrick(point, size);
    }

    /**
     * This method will execute the parent impact() method.
     * It gets the current strength of the brick in
     * order to change the colour of the respective brick.
     */
    public void impact() {
        super.impact();
        HashMap<Integer, Color> hp = new HashMap<>();
        hp.put(1, new Color(0, 153, 0));
        hp.put(2, new Color(255, 255, 0));
        setInner(hp.get(super.getStrength()));
    }

    /**
     * This method will execute the parent repair() method.
     * It sets the brick back to its default colour.
     */

    public void repair() {
        super.repair();
        super.setInner(new Color(204, 0, 0));
    }
}

