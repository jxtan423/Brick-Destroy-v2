package App.Graphics.Frame.InGame.View.Brick;

import App.Graphics.Frame.InGame.View.Ball.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * This abstract class inherits JComponent.
 * Every brick has its own strength.
 * The bricks will be displayed on user's screen
 * and different types of bricks will be displayed
 * depending on level for normal game.
 */

public abstract class Brick extends JComponent {

    public static final int UP_IMPACT = 0;
    public static final int DOWN_IMPACT = 1;
    public static final int LEFT_IMPACT = 2;
    public static final int RIGHT_IMPACT = 3;

    private final Color border;
    private Color inner;

    private final int fullStrength;
    private int strength;
    private boolean broken;

    private Shape brickFace;

    /**
     * This constructor initialized the boolean
     * variable as false.
     * The brickFace is created through makeBrickFace
     * method by passing 2 arguments to the method.
     * The border and inner are assigned from the
     * parameters value as well as the strength.
     *
     * @param pos The coordinates of brick
     * @param size The size of brick
     * @param border The border colour of brick
     * @param inner The inner colour of brick
     * @param strength The strength of the brick (The lives)
     */

    public Brick(Point pos, Dimension size, Color border, Color inner, int strength) {
        broken = false;
        brickFace = makeBrickFace(pos, size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    /**
     * This method will execute the drawBricks
     * method.
     *
     * @param g The graphics context on which to paint
     */

    public void paint(Graphics g) {
        drawBricks((Graphics2D) g);
    }

    /**
     * This method will set the inner and border
     * colour which is initialized in the early
     * stage by painting and drawing
     * the brick respectively.
     *
     * @param g2d The graphics context on which to paint in 2D form
     */

    public void drawBricks(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(inner);
        g2d.fill(getBrick());
        g2d.setColor(border);
        g2d.draw(getBrick());
        g2d.setColor(tmp);
    }

    /**
     * This is a protected abstract method
     * which will be implemented on child class.
     *
     * @param pos The coordinates of brick
     * @param size The size of brick
     * @return The shape of the brick
     */

    protected abstract Shape makeBrickFace(Point pos, Dimension size);

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
        if (broken)
            return false;
        impact();
        return broken;
    }

    /**
     * This abstract method will be
     * implemented on child class.
     *
     * @return The shape of the brick
     */

    public abstract Shape getBrick();

    /**
     * This method is to get the border
     * colour of the respective brick.
     *
     * @return The border colour of the brick
     */

    public Color getBorderColor() {
        return border;
    }

    /**
     * This method is to get the inner
     * colour of the respective brick.
     *
     * @return The inner colour of the brick
     */

    public Color getInnerColor() {
        return inner;
    }

    /**
     * This method is to identify which
     * part of the brick has been hit
     * by the ball.
     *
     * @param b The ball
     * @return The direction impact of the brick
     */

    public final int findImpact(Ball b) {
        int OUTPUT = 4;
        if (broken)
            return OUTPUT;
        if (brickFace.contains(b.getRight()))
            OUTPUT = LEFT_IMPACT;
        else if (brickFace.contains(b.getLeft()))
            OUTPUT = RIGHT_IMPACT;
        else if (brickFace.contains(b.getUp()))
            OUTPUT = DOWN_IMPACT;
        else if (brickFace.contains(b.getDown()))
            OUTPUT = UP_IMPACT;
        return OUTPUT;
    }

    /**
     * This method is to get the current state of the
     * respective brick on whether the brick is
     * broken or not.
     *
     * @return The boolean value where the brick is broken or no
     */

    public final boolean isBroken() {
        return broken;
    }

    /**
     * This method is to assigned the
     * default strength of the
     * brick which get from the
     * parameters in the early stage
     * to reset the brick back to normal,
     * which is not broken.
     */

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * This method is to set the strength
     * deduct by 1.
     * Whenever the strength is 0, the
     * boolean variable will automatically
     * become true.
     */

    public void impact() {
        strength--;
        broken = (strength == 0);
    }

    /**
     * This method is to get the current
     * strength A.K.A the lives of the
     * ball.
     *
     * @return The strength of the brick (the lives)
     */

    public int getStrength() {
        return strength;
    }

    /**
     * This method is to set the inner colour
     * of the brick regarding to the
     * parameter's value.
     *
     * @param inner The inner colour of the brick
     */
    public void setInner(Color inner) {
        this.inner = inner;
    }

    public Shape getBrickFace() {
        return brickFace;
    }
}