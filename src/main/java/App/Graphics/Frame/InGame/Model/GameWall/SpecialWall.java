package App.Graphics.Frame.InGame.Model.GameWall;

import App.Graphics.Frame.InGame.View.Brick.Brick;
import App.Graphics.Frame.InGame.View.Ball.RonaldoBall;
import App.Graphics.Frame.InGame.View.Ball.RubberBall;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * This class inherits the Wall class
 * and gets the attributes from the parents
 * class.
 * This class has only 1 level and it has
 * green,yellow and red brick.
 * This class will display 3 different colours
 * of bricks, the player and the balls along with
 * parents' attributes.
 */

public class SpecialWall extends Wall {

    private static final int LEVELS_COUNT = 1;
    private static final int GREEN = 4;
    private static final int YELLOW = 5;
    private static final int RED = 6;

    private boolean isCR7Time;

    /**
     * This constructor calls its parents to create
     * the properties inside its parents' class.
     * The ball is set to be a Rubber ball by
     * creating the RubberBall object.
     *
     * @param drawArea The area of the frame
     * @param brickCount The total number of the bricks
     * @param lineCount The rows of the bricks
     * @param brickDimensionRatio The ratio of the bricks
     * @param ballPos The initial coordinates for the ball
     */

    public SpecialWall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        super(drawArea, brickCount, lineCount, brickDimensionRatio, ballPos);
        super.setBall(new RubberBall(ballPos));
    }

    /**
     * This method will execute the createBricks method
     * which is the abstract method from the parent class.
     * This method will only create 1 single level.
     *
     * @param drawArea The area of the frame
     * @param brickCount The total number of the bricks
     * @param lineCount The rows of the bricks
     * @param brickDimensionRatio The ratio of the bricks
     * @return The 2D array of the bricks
     */

    @Override
    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, GREEN, YELLOW, RED);
        return tmp;
    }

    /**
     * This method will create a CreationOfBricks
     * object to create the bricks with specific
     * properties which is the green, yellow, and
     * red bricks.
     *
     * @param drawArea The area of the frame
     * @param brickCnt The total number of the bricks
     * @param lineCnt The rows of the bricks
     * @param brickSizeRatio The ratio of the bricks
     * @param typeA Type A bricks
     * @param typeB Type B bricks
     * @param typeC Type C bricks
     * @return The array of the bricks
     */

    @Override
    public Brick[] createBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC) {
        CreationOfBricks CoB = new CreationOfBricks(drawArea, brickCnt, lineCnt, brickSizeRatio, typeA, typeB, typeC);
        return CoB.getTmp();
    }

    /**
     * Set the current ball to RonaldoBall
     * at the current position.
     * The speed of ball is set to (2,2).
     * The boolean variable will be assigned to
     * true.
     *
     * @param point The ball coordinate in 2D form
     */

    public void setCR7Ball(Point2D point) {
        super.setBall(new RonaldoBall(point));
        super.getBall().setSpeed(2, 2);
        isCR7Time = true;
    }

    /**
     * Set the current ball to RubberBall
     * at the current position.
     * The boolean variable will be assigned to
     * false.
     *
     * @param point The ball coordinate in 2D form
     */

    public void setRubberBall(Point2D point) {
        super.setBall(new RubberBall(point));
        isCR7Time = false;
    }

    /**
     * This method will overrides the parents' method.
     * Whenever the ball collides with the bricks, the
     * score will add 2 for each brick when the boolean
     * variable is true where the ball is RonaldoBall.
     * If it is RubberBall, it uses the parents' method instead
     * of this method.
     *
     * @return The boolean value to identify whether the ball collides any bricks
     */

    public boolean impactWall() {
        int IMPACT_NOT_FOUND = 4;
        if (isCR7Time) {
            for (Brick b : super.getBricks()) {
                if (b.findImpact(super.getBall()) < IMPACT_NOT_FOUND) {
                    score+= 2;
                    return ImpactDirection.values()[b.findImpact(super.getBall())].setImpact(b, super.getBall(), false);
                }
            }
            return false;
        } else
            return super.impactWall();
    }
}

