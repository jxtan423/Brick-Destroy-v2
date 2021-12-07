package test;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

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

    public CreationOfBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC) {
        area = drawArea;
        AmountOfBrick = brickCnt;
        LinesOfBrick = lineCnt;
        SizeRatio = brickSizeRatio;
        brickCreation(typeA, typeB, typeC);
    }

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
            if(noTypeCFound(typeC)) {
                HashMap<Boolean, Brick> hashMap = new HashMap<>();
                hashMap.put(Boolean.TRUE,makeSpecificBrick(p, brickSize, typeA));
                hashMap.put(Boolean.FALSE, makeSpecificBrick(p, brickSize, typeB));
                tmp[i] = hashMap.get(identifySequenceOfBricks(brickOnLine, line, posX, i));
            }
            else{
                Random rnd = new Random();
                int number = rnd.nextInt(3) + 4;
                HashMap<Integer, Brick> hashMap = new HashMap<>();
                hashMap.put(GREEN, makeSpecificBrick(p, brickSize, typeA));
                hashMap.put(YELLOW, makeSpecificBrick(p, brickSize, typeB));
                hashMap.put(RED, makeSpecificBrick(p, brickSize, typeC));
                tmp[i] = hashMap.get(number);
            }
        }
        for (y = brickHgt; i < tmp.length; i++, y += NextOddLine(brickHgt)) {
            x = identifyLastBrickOnX(brickOnLine, brickLen);
            p.setLocation(x, y);
            tmp[i] = makeSpecificBrick(p, brickSize, typeA);
        }
        setTmp(tmp);
    }

    public void setTmp(Brick[] tmp) {
        allBricks = tmp;
    }

    public Brick[] getTmp() {
        return allBricks;
    }

    private boolean noTypeCFound(int typeC) {
        return typeC == 0;
    }

    private boolean ReachMaxLine(int line) {
        return line == LinesOfBrick;
    }

    private int AdjustBrickCount() {
        return (AmountOfBrick - (AmountOfBrick % LinesOfBrick)) + LinesOfBrick / 2;
    }

    private int CountBricksOnSingleLine() {
        return AmountOfBrick / LinesOfBrick;
    }

    private double CalculateBrickLen(int brickOnLine) {
        return area.getWidth() / brickOnLine;
    }

    private double CalculateBrickHgt(double brickLen) {
        return brickLen / SizeRatio;
    }

    private int identifyLine(int i, int brickOnLine) {
        return i / brickOnLine;
    }

    private int identifyPositionX(int i, int brickOnLine) {
        return i % brickOnLine;
    }

    private double createBrickOnY(int line, double brickHgt) {
        return line * brickHgt;
    }

    private double createBrickOnX(int posX, double brickLen, int line) {
        double x = posX * brickLen;
        return (line % 2 == 0) ? x : (x - (brickLen / 2));
    }

    private Boolean identifySequenceOfBricks(int brickOnLine, int line, int posX, int i) {
        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;
        return ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
    }

    private double identifyLastBrickOnX(int brickOnLine, double brickLen) {
        return (brickOnLine * brickLen) - (brickLen / 2);
    }

    private double NextOddLine(double brickHgt) {
        return (2 * brickHgt);
    }

    public Point getP() {
        return p;
    }

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
}