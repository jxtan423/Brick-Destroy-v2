package App.Graphics.Frame.InGame.View;

import javax.swing.*;
import java.awt.*;

/**
 * This class inherits the JComponent.
 * This class will display the player
 * in terms of colour, size and position.
 */

public class Player extends JComponent {

    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;

    /**
     * This constructor initialize the ballpoint position
     * from the parameter and assigned moveAmount as 0.
     * PlayerFace is assigned by executing the
     * makeRectangle method.
     * min and max are assigned as well.
     *
     * @param ballPoint The initial point of the ball
     * @param width The width of the player
     * @param height The height of the player
     * @param container The area of the frame
     */

    public Player(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;
    }

    /**
     * This method is to create a rectangle
     * with specific size and position.
     *
     * @param width The width of the player
     * @param height The height of the player
     * @return The complete rectangle in terms of size and position
     */

    private Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (ballPoint.getX() - (width / 2)), (int) ballPoint.getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    /**
     * This method is to detect whether the ball
     * collides with the player.
     *
     * @param b The ball
     * @return Boolean value where the ball has or has not collides with the player
     */

    public boolean impact(Ball b) {
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down);
    }

    /**
     * This method is to make the player
     * moves horizontally.
     * Make sure the player unable to move through
     * the border of the frame.
     *
     */

    public void move() {
        double x = ballPoint.getX() + moveAmount;
        if (x < min || x > max)
            return;
        ballPoint.setLocation(x, ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int) playerFace.getWidth() / 2, ballPoint.y);
    }

    /**
     * This method is to move
     * the player to the left.
     */

    public void moveLeft() {
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * This method is to move
     * the player to the right.
     */

    public void movRight() {
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * This method is to make
     * the player remain the
     * position, cannot be moved.
     */

    public void stop() {
        moveAmount = 0;
    }

    /**
     * This method is to get the
     * shape of the player.
     *
     * @return The shape of the player
     */

    public Shape getPlayerFace() {
        return playerFace;
    }

    /**
     * This method is to set the
     * player to back to its
     * initial coordinates, which
     * is the initial point where
     * the game starts.
     *
     * @param p The initial point
     */

    public void moveTo(Point p) {
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int) playerFace.getWidth() / 2, ballPoint.y);
    }

    /**
     * This method will execute
     * the drawPlayer method.
     * @param g The graphics context on which to paint
     */

    public void paint(Graphics g) {
        drawPlayer((Graphics2D) g);
    }

    /**
     * This method will set the inner and border
     * colour which is initialized in the early
     * stage by painting and drawing
     * the player respectively.
     *
     * @param g2d The graphics context on which to paint in 2D form
     */

    private void drawPlayer(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(INNER_COLOR);
        g2d.fill(playerFace);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(playerFace);
        g2d.setColor(tmp);
    }
}
