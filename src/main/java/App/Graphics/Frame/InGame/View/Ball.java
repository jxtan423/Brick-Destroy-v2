package App.Graphics.Frame.InGame.View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This abstract class inherits JComponent.
 * This ball class will be defined in terms
 * of radius.
 */

public abstract class Ball extends JComponent {

    private Shape ballFace;

    private final Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private final Color border;
    private final Color inner;

    private int speedX;
    private int speedY;

    /**
     * This constructor will get the 2D point in terms
     * of up, down, left and right.
     * The shape of the circle as well will be created.
     * The speed of the ball is initialized as 0.
     * The inner colour and border colour are assigned
     * from the value of parameters.
     *
     * @param center The center point of the ball
     * @param radiusA The radius of the ball
     * @param radiusB The radius of the ball
     * @param inner The inner colour of the ball
     * @param border The border colour of the ball
     */

    public Ball(Point2D center, int radiusA, int radiusB, Color inner, Color border) {
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(), center.getY() - (radiusB / 2));
        down.setLocation(center.getX(), center.getY() + (radiusB / 2));

        left.setLocation(center.getX() - (radiusA / 2), center.getY());
        right.setLocation(center.getX() + (radiusA / 2), center.getY());

        ballFace = makeBall(center, radiusA, radiusB);
        this.border = border;
        this.inner = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * This is a protected abstract method
     * for the child class to implement.
     *
     * @param center The center point of the ball
     * @param radiusA The radius of the ball
     * @param radiusB The radius of the ball
     * @return The shape of the ball
     */

    protected abstract Shape makeBall(Point2D center, int radiusA, int radiusB);

    /**
     * This method is to make the ball
     * moves by adding the speedX and speedY
     * and keep updating the state followed by
     * some calculation.
     */

    public void move() {
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX), (center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() - (w / 2)), (center.getY() - (h / 2)), w, h);
        setPoints(w, h);

        ballFace = tmp;
    }

    /**
     * This method is to set the
     * x and y coordinates speed.
     *
     * @param x The x coordinates speed
     * @param y The y coordinates speed
     */

    public void setSpeed(int x, int y) {
        speedX = x;
        speedY = y;
    }

    /**
     * This method is to set
     * the x coordinates speed.
     *
     * @param s The x coordinates speed
     */

    public void setXSpeed(int s) {
        speedX = s;
    }

    /**
     * This method is to set
     * the y coordinates speed.
     *
     * @param s The y coordinates speed
     */

    public void setYSpeed(int s) {
        speedY = s;
    }

    /**
     * This method is to set the
     * x coordinate speed to opposite
     * value.
     */

    public void reverseX() {
        speedX *= -1;
    }

    /**
     * This method is to set the
     * y coordinate speed to opposite
     * value.
     */

    public void reverseY() {
        speedY *= -1;
    }

    /**
     * This method is to get the center
     * point of the ball.
     *
     * @return The center point of the ball
     */
    public Point2D getPosition() {
        return center;
    }

    /**
     * This method is to get the
     * shape of the ball.
     * @return The shape of the ball
     */

    public Shape getBallFace() {
        return ballFace;
    }

    /**
     * This method is to set the ball to a
     * particular point regarding to the
     * parameter value.
     *
     * @param p The initial/starting point
     */

    public void moveTo(Point p) {
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() - (w / 2)), (center.getY() - (h / 2)), w, h);
        ballFace = tmp;
    }

    /**
     * This method is to set a new
     * point for the 2D point.
     *
     * @param width The ball width
     * @param height The ball height
     */

    private void setPoints(double width, double height) {
        up.setLocation(center.getX(), center.getY() - (height / 2));
        down.setLocation(center.getX(), center.getY() + (height) / 10);
        left.setLocation(center.getX() - (width / 2), center.getY());
        right.setLocation(center.getX() + (width / 2), center.getY());
    }

    /**
     * This method is to get the
     * x coordinate speed.
     *
     * @return The x coordinate speed
     */

    public int getSpeedX() {
        return speedX;
    }

    /**
     * This method is to get the
     * y coordinate speed.
     *
     * @return The y coordinate speed
     */

    public int getSpeedY() {
        return speedY;
    }

    /**
     * This method will execute the drawBall
     * method.
     *
     * @param g The graphics context on which to paint
     */

    public void paint(Graphics g) {
        drawBall((Graphics2D)g);
    }

    /**
     * This method will set the inner and border
     * colour which is initialized in the early
     * stage by painting and drawing
     * the ball respectively.
     *
     * @param g2d The graphics context on which to paint in 2D form
     */

    private void drawBall(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        Shape s = getBallFace();
        g2d.setColor(inner);
        g2d.fill(s);
        g2d.setColor(border);
        g2d.draw(s);
        g2d.setColor(tmp);
    }
}
