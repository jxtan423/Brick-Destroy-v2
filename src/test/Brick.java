package test;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

public abstract class Brick extends JComponent {

    public static final int UP_IMPACT = 0;
    public static final int DOWN_IMPACT = 1;
    public static final int LEFT_IMPACT = 2;
    public static final int RIGHT_IMPACT = 3;

    public static Random rnd;

    private final Color border;
    private Color inner;

    private final int fullStrength;
    private int strength;
    private boolean broken;

    Shape brickFace;

    public Brick(Point pos, Dimension size, Color border, Color inner, int strength) {
        rnd = new Random();
        broken = false;
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
        int OUTPUT = 4;
        if (broken)
            return OUTPUT;
        if (brickFace.contains(b.right))
            OUTPUT = LEFT_IMPACT;
        else if (brickFace.contains(b.left))
            OUTPUT = RIGHT_IMPACT;
        else if (brickFace.contains(b.up))
            OUTPUT = DOWN_IMPACT;
        else if (brickFace.contains(b.down))
            OUTPUT = UP_IMPACT;
        return OUTPUT;
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