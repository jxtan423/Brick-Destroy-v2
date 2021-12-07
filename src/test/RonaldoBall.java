package test;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class RonaldoBall extends Ball {

    private static final int DEF_RADIUS = 30;
    private static final Color DEF_INNER_COLOR = new Color(255,255,255);
    private static final Color DEF_BORDER_COLOR = new Color(0,0,0);

    public RonaldoBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_RADIUS, DEF_INNER_COLOR, DEF_BORDER_COLOR);
    }

    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {
        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x, y, radiusA, radiusB);
    }

    public void paint(Graphics g) {
        drawBall((Graphics2D)g);
    }

    private void drawBall(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        Shape s = getBallFace();
        g2d.setColor(DEF_INNER_COLOR);
        g2d.fill(s);
        g2d.setColor(DEF_BORDER_COLOR);
        g2d.draw(s);
        g2d.setColor(tmp);
    }
}
