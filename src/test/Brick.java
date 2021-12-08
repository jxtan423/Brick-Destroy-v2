package test;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 */
public abstract class Brick extends JComponent {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    public static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    public Brick(String name, Point pos, Dimension size, Color border, Color inner, int strength) {
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos, size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    public void paint(Graphics g) {
        drawBricks((Graphics2D) g);
    }


    public void drawBricks(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(inner);
        g2d.fill(getBrick());
        g2d.setColor(border);
        g2d.draw(getBrick());
        g2d.setColor(tmp);
    }



    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    public boolean setImpact(Point2D point, int dir) {
        if (broken)
            return false;
        impact();
        return broken;
    }

    public abstract Shape getBrick();


    public Color getBorderColor() {
        return border;
    }

    public Color getInnerColor() {
        return inner;
    }

    public final int findImpact(Ball b) {
        if (broken)
            return 0;
        int out = 0;
        if (brickFace.contains(b.right))
            out = LEFT_IMPACT;

        else if (brickFace.contains(b.left))
            out = RIGHT_IMPACT;

        else if (brickFace.contains(b.up))
            out = DOWN_IMPACT;

        else if (brickFace.contains(b.down))
            out = UP_IMPACT;

        return out;
    }

    public final boolean isBroken() {
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact() {
        strength--;
        broken = (strength == 0);
    }

    public int getStrength() {
        return strength;
    }

    public void setInner(Color inner) {
        this.inner = inner;
    }

}





