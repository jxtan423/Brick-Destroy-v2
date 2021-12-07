package test;

import java.awt.*;
import java.util.HashMap;

public class RedBrick extends Brick implements DetermineBricks {

    private static final String NAME = "Red Brick";
    private static Color DEF_INNER = new Color(204, 0, 0);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STRENGTH = 3;

    public RedBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, STRENGTH);
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
        return new RedBrick(point, size);
    }

    public void impact() {
        super.impact();
        HashMap<Integer, Color> hp = new HashMap<>();
        hp.put(1,new Color(0, 153, 0));
        hp.put(2,new Color(255, 255, 0));
        setInner(hp.get(super.getStrength()));
    }

   public void repair() {
        super.repair();
        super.setInner(new Color(204,0,0));
    }
}

