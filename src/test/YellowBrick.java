package test;

import java.awt.*;

public class YellowBrick extends Brick implements DetermineBricks {

    private static final Color DEF_INNER = new Color(255, 255, 0);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CLAY_STRENGTH = 2;

    public YellowBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
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
        return new YellowBrick(point, size);
    }

    public void impact() {
        super.impact();
        super.setInner(new Color(0, 153, 0));
    }

    public void repair() {
        super.repair();
        super.setInner(new Color(255, 255, 0));
    }
}