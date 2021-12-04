package test;

import java.awt.*;
import java.util.HashMap;

public class CreationOfBricks {

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private Brick[] allBricks;
    private final Rectangle area;
    private int AmountOfBrick;
    private final int LinesOfBrick;
    private final double SizeRatio;

    public CreationOfBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        area = drawArea;
        AmountOfBrick = brickCnt;
        LinesOfBrick = lineCnt;
        SizeRatio = brickSizeRatio;
        brickCreation(typeA, typeB);
    }

    private void brickCreation(int typeA, int typeB) {
        AmountOfBrick = AdjustBrickCount(AmountOfBrick, LinesOfBrick);
        int brickOnLine = CountBricksOnSingleLine(AmountOfBrick, LinesOfBrick);
        double brickLen = CalculateBrickLen(brickOnLine, area);
        double brickHgt = CalculateBrickHgt(brickLen, SizeRatio);

        Brick[] tmp = new Brick[AmountOfBrick];
        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        double y;
        double x;

        for (i = 0; i < AmountOfBrick; i++) {
            int line = identifyLine(i, brickOnLine);
            if (ReachMaxLine(line, LinesOfBrick))
                break;
            int posX = identifyPositionX(i, brickOnLine);
            x = createBrickOnX(posX, brickLen, line);
            y = createBrickOnY(line, brickHgt);
            p.setLocation(x, y);
            if (haveTwoTypesOfBrick(typeA, typeB)) {
                boolean isTypeA = identifySequenceOfBricks(brickOnLine, line, posX, i);
                if (isTypeA)
                    tmp[i] = makeSpecificBrick(p, brickSize, typeA);
                else
                    tmp[i] = makeSpecificBrick(p, brickSize, typeB);
            } else
                tmp[i] = makeSpecificBrick(p, brickSize, typeA);
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

    private boolean ReachMaxLine(int line, int lineCnt) {
        return line == lineCnt;
    }

    private int AdjustBrickCount(int brickCnt, int lineCnt) {
        return (brickCnt - (brickCnt % lineCnt)) + lineCnt / 2;
    }

    private int CountBricksOnSingleLine(int brickCnt, int lineCnt) {
        return brickCnt / lineCnt;
    }

    private double CalculateBrickLen(int brickOnLine, Rectangle drawArea) {
        return drawArea.getWidth() / brickOnLine;
    }

    private double CalculateBrickHgt(double brickLen, double brickSizeRatio) {
        return brickLen / brickSizeRatio;
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

    private boolean haveTwoTypesOfBrick(int typeA, int typeB) {
        return typeA != 0 && typeB != 0;
    }

    private boolean identifySequenceOfBricks(int brickOnLine, int line, int posX, int i) {
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

    private Brick makeSpecificBrick(Point point, Dimension size, int type) {
        HashMap<Integer, DetermineBricks> hM = new HashMap<>();
        hM.put(CLAY, new ClayBrick(point, size));
        hM.put(STEEL, new SteelBrick(point, size));
        hM.put(CEMENT, new CementBrick(point, size));
        DetermineBricks db = hM.get(type);
        return db.getSpecificBrick(point, size);
    }
}