package App.Graphics.Frame.InGame.Model;

import App.Graphics.Frame.InGame.DetermineBricks;
import App.Graphics.Frame.InGame.View.*;
import App.Graphics.Frame.InGame.View.CementBrick;
import App.Graphics.Frame.InGame.View.ClayBrick;
import App.Graphics.Frame.InGame.View.GreenBrick;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

/**
 * This class is to create the bricks
 * in the form of array and each of the bricks
 * has its own coordinates and same length and
 * height.
 * There will be different types of bricks.
 */

public class CreationOfBricks {

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int GREEN = 4;
    private static final int YELLOW = 5;
    private static final int RED = 6;

    private Brick[] allBricks;
    private final Rectangle area;
    private int AmountOfBrick;
    private final int LinesOfBrick;
    private final double SizeRatio;

    private Point p;

    /**
     * This method will assigned the variables from
     * the parameters and execute the brickCreation
     * method with typeA, typeB and typeC pass to the
     * method as value.
     *
     * @param drawArea The area of the frame
     * @param brickCnt The total number of the bricks
     * @param lineCnt The rows of the bricks
     * @param brickSizeRatio The ratio of the bricks
     * @param typeA Type A bricks
     * @param typeB Type B bricks
     * @param typeC Type C bricks
     */

    public CreationOfBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC) {
        area = drawArea;
        AmountOfBrick = brickCnt;
        LinesOfBrick = lineCnt;
        SizeRatio = brickSizeRatio;
        brickCreation(typeA, typeB, typeC);
    }

    /**
     * This method identifies the position of the bricks
     * along with height and length.
     * The array of the brick is set regarding to
     * the number of bricks that want to be created.
     * Numerous methods will be executed through this method
     * to ensure the coordinates of every brick is different.
     * Special Game and Normal Game's bricks will be
     * separated and executed its respective methods.
     * The odd row and even row bricks have different coordinates
     * in terms of x coordinate where the first brick for odd row
     * spawned at the 0 position while the first brick for
     * even row spawned at -30 position.
     * The last brick will be created at the last odd row since it
     * only show half of the brick for the first brick at odd row.
     * The remaining half is shown by creating a new single brick
     * but just display only half of the brick for the last odd row.
     *
     * @param typeA Type A bricks
     * @param typeB Type B bricks
     * @param typeC Type C bricks
     */

    private void brickCreation(int typeA, int typeB, int typeC) {
        AmountOfBrick = AdjustBrickCount();
        int brickOnLine = CountBricksOnSingleLine();
        double brickLen = CalculateBrickLen(brickOnLine);
        double brickHgt = CalculateBrickHgt(brickLen);

        Brick[] tmp = new Brick[AmountOfBrick];
        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        p = new Point();

        int i;
        double y;
        double x;

        for (i = 0; i < AmountOfBrick; i++) {
            int line = identifyLine(i, brickOnLine);
            if (ReachMaxLine(line))
                break;
            int posX = identifyPositionX(i, brickOnLine);
            x = createBrickOnX(posX, brickLen, line);
            y = createBrickOnY(line, brickHgt);
            p.setLocation(x, y);
            if (noTypeCFound(typeC))
                tmp[i] = createNormalBrick(typeA, typeB, brickSize, brickOnLine, line, posX, i);
            else
                tmp[i] = createSpecialBrick(typeA, typeB, typeC, brickSize);
        }
        for (y = brickHgt; i < tmp.length; i++, y += NextOddLine(brickHgt)) {
            x = identifyLastBrickOnX(brickOnLine, brickLen);
            p.setLocation(x, y);
            tmp[i] = makeSpecificBrick(p, brickSize, typeA);
        }
        setTmp(tmp);
    }

    /**
     * This method set the array of the brick
     * for the level.
     * @param tmp The array of the brick
     */

    public void setTmp(Brick[] tmp) {
        allBricks = tmp;
    }

    /**
     * @return The array of the brick
     */

    public Brick[] getTmp() {
        return allBricks;
    }

    /**
     * This method is to identify whether
     * the typeC brick exists or no.
     *
     * @param typeC Type C bricks
     * @return true when typeC is 0
     */

    private boolean noTypeCFound(int typeC) {
        return typeC == 0;
    }

    /**
     * This method identify whether the current row has reached
     * the maximum row of the bricks.
     *
     * @param line The current row of the brick
     * @return The boolean value where the current row
     * of brick has or has not reached the given row
     * set by the programmer
     */

    private boolean ReachMaxLine(int line) {
        return line == LinesOfBrick;
    }

    /**
     * This method is to adjust the number of bricks
     *
     * @return The adjusted number of bricks
     */

    private int AdjustBrickCount() {
        return (AmountOfBrick - (AmountOfBrick % LinesOfBrick)) + LinesOfBrick / 2;
    }

    /**
     * This method is to identify how many bricks
     * should be created in a single row.
     *
     * @return The number of bricks in a single row
     */
    private int CountBricksOnSingleLine() {
        return AmountOfBrick / LinesOfBrick;
    }

    /**
     * This method is to calculate the length
     * of the brick.
     *
     * @param brickOnLine The amount of brick on every row
     * @return The length of the brick
     */

    private double CalculateBrickLen(int brickOnLine) {
        return area.getWidth() / brickOnLine;
    }

    /**
     * This method is to calculate the height
     * of the brick.
     *
     * @param brickLen The length of the brick
     * @return The height of the brick
     */

    private double CalculateBrickHgt(double brickLen) {
        return brickLen / SizeRatio;
    }

    /**
     * To Identify which row the current index is
     *
     * @param i The current index of the line
     * @param brickOnLine The amount of brick on every row
     * @return The current index's row
     */

    private int identifyLine(int i, int brickOnLine) {
        return i / brickOnLine;
    }

    /**
     * This method is to identify the
     * x coordinates.
     *
     * @param i The current index of the line
     * @param brickOnLine The amount of brick on every row
     * @return The X coordinate
     */

    private int identifyPositionX(int i, int brickOnLine) {
        return i % brickOnLine;
    }

    /**
     * This method is to identify the y
     * coordinates of the brick.
     *
     * @param line The current row of the brick
     * @param brickHgt The height of the brick
     * @return The y coordinates
     */

    private double createBrickOnY(int line, double brickHgt) {
        return line * brickHgt;
    }

    /**
     * This method is to identify the x
     * coordinates of the brick.
     *
     * @param posX The position of X
     * @param brickLen The length of the brick
     * @param line The current row of the brick
     * @return The x coordinates
     */

    private double createBrickOnX(int posX, double brickLen, int line) {
        double x = posX * brickLen;
        return (line % 2 == 0) ? x : (x - (brickLen / 2));
    }

    /**
     * To identify the sequence of the brick for
     * the sequence of typeA and typeB created
     *
     * @param brickOnLine The amount of brick on every row
     * @param line The current row of the brick
     * @param posX The position of X
     * @param i The current index of the line
     * @return The boolean value
     */

    private Boolean identifySequenceOfBricks(int brickOnLine, int line, int posX, int i) {
        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;
        return ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
    }

    /**
     * This method is to identify the last
     * brick in terms of x coordinates by
     * using the algorithms below.
     *
     * @param brickOnLine The amount of brick on every row
     * @param brickLen The length of the brick
     * @return The X coordinates of the last brick
     */

    private double identifyLastBrickOnX(int brickOnLine, double brickLen) {
        return (brickOnLine * brickLen) - (brickLen / 2);
    }

    /**
     * This method is to jump to the subsequent
     * odd row of the bricks.
     *
     * @param brickHgt The height of the brick
     * @return The y value of the next odd line
     */

    private double NextOddLine(double brickHgt) {
        return (2 * brickHgt);
    }

    /**
     * @return The point of the respective bricks
     */

    public Point getP() {
        return p;
    }

    /**
     * This method is to identify which types of
     * brick will be created depending on the
     * parameter given.
     *
     * @param point The point of the respective bricks
     * @param size The size of the bricks
     * @param type The type of the bricks
     * @return The specific types of brick that will be created
     */

    private Brick makeSpecificBrick(Point point, Dimension size, int type) {
        HashMap<Integer, DetermineBricks> hM = new HashMap<>();
        hM.put(CLAY, new ClayBrick(point, size));
        hM.put(STEEL, new SteelBrick(point, size));
        hM.put(CEMENT, new CementBrick(point, size));
        hM.put(GREEN, new GreenBrick(point, size));
        hM.put(YELLOW, new YellowBrick(point, size));
        hM.put(RED, new RedBrick(point, size));
        DetermineBricks db = hM.get(type);
        return db.getSpecificBrick(point, size);
    }

    /**
     * This method is to identify whether is typeA or
     * typeB brick will be chosen to be created depending on
     * boolean value from the identifySequenceOfBrick
     * method.
     *
     * @param typeA Type A bricks
     * @param typeB Type B bricks
     * @param brickSize The size of the brick
     * @param brickOnLine The amount of brick on every row
     * @param line The current row of the brick
     * @param posX The position of x
     * @param i The current index of the line
     * @return The specific brick that is created
     */

    private Brick createNormalBrick(int typeA, int typeB, Dimension brickSize, int brickOnLine, int line, int posX, int i) {
        HashMap<Boolean, Brick> hashMap = new HashMap<>();
        hashMap.put(Boolean.TRUE, makeSpecificBrick(p, brickSize, typeA));
        hashMap.put(Boolean.FALSE, makeSpecificBrick(p, brickSize, typeB));
        return hashMap.get(identifySequenceOfBricks(brickOnLine, line, posX, i));
    }

    /**
     * This method is to identify which type of
     * brick will be chosen to be created depending
     * on the parameter type.
     *
     * @param typeA Type A bricks
     * @param typeB Type B bricks
     * @param typeC Type C bricks
     * @param brickSize The size of the brick
     * @return The specific brick that is created
     */

    private Brick createSpecialBrick(int typeA, int typeB, int typeC, Dimension brickSize) {
        Random rnd = new Random();
        int number = rnd.nextInt(3) + 4;
        HashMap<Integer, Brick> hashMap = new HashMap<>();
        hashMap.put(GREEN, makeSpecificBrick(p, brickSize, typeA));
        hashMap.put(YELLOW, makeSpecificBrick(p, brickSize, typeB));
        hashMap.put(RED, makeSpecificBrick(p, brickSize, typeC));
        return hashMap.get(number);
    }
}