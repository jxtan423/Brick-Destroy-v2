package App.Graphics.Frame.InGame.View.Ball;

import App.Graphics.Frame.InGame.View.Ball.Ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * This class inherits the Ball class to get
 * its parents' attributes.
 * This class has its own radius as well as
 * the colours.
 * This class is to create a ball with specific
 * colour and size
 */

public class RonaldoBall extends Ball {

    private static final int DEF_RADIUS = 30;
    private static final Color DEF_INNER_COLOR = new Color(255, 255, 255);
    private static final Color DEF_BORDER_COLOR = new Color(0, 0, 0);

    /**
     * This constructor is to call its parents' constructor
     * and pass the required arguments to the parents.
     *
     * @param center The center point of the ball
     */

    public RonaldoBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_RADIUS, DEF_INNER_COLOR, DEF_BORDER_COLOR);
    }

    /**
     * This method will create the structure of the
     * ball with specific radius along with center point.
     *
     * @param center The center point of the ball
     * @param radiusA The radius of the ball
     * @param radiusB The radius of the ball
     * @return The shape of the ball
     */
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {
        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x, y, radiusA, radiusB);
    }
}
