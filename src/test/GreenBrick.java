package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class GreenBrick extends Brick implements DetermineBricks {

    private static final String NAME = "Green Brick";
    private static final Color DEF_INNER = new Color(0, 153, 0);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CLAY_STRENGTH = 1;

    public GreenBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

    @Override
    public Brick getSpecificBrick(Point point, Dimension size) {
        return new GreenBrick(point, size);
    }
}
