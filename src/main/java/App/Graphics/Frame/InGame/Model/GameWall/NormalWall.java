package App.Graphics.Frame.InGame.Model.GameWall;

import App.Graphics.Frame.InGame.View.Brick.Brick;
import App.Graphics.Frame.InGame.View.Ball.RubberBall;
import java.awt.*;

/**
 * This class inherits the Wall class
 * and gets the attributes from the parents
 * class.
 * This class has 4 levels and it has
 * clay,cement and steel brick.
 * This class will display 3 different colours
 * of bricks, the player and the balls along with
 * parents' attributes.
 */

public class NormalWall extends Wall {

    private static final int LEVELS_COUNT = 4;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

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

    public NormalWall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        super(drawArea, brickCount, lineCount, brickDimensionRatio, ballPos);
        super.setBall(new RubberBall(ballPos));
    }

    /**
     * This method will execute the createBricks method
     * which is the abstract method from the parent class.
     * This method will create 4 levels.
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
        tmp[0] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CLAY, 0);
        tmp[1] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT, 0);
        tmp[2] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL, 0);
        tmp[3] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT, 0);
        return tmp;
    }

    /**
     * This method will create a CreationOfBricks
     * object to create the bricks with specific
     * properties which is the clay, cement, and
     * steel bricks.
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
}
