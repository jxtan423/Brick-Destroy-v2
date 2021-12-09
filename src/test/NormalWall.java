package test;

import java.awt.*;

public class NormalWall extends Wall {

    private static final int LEVELS_COUNT = 4;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    public NormalWall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        super(drawArea, brickCount, lineCount, brickDimensionRatio, ballPos);
        super.setBall(new RubberBall(ballPos));
    }

    @Override
    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CLAY, 0);
        tmp[1] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT, 0);
        tmp[2] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL, 0);
        tmp[3] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT, 0);
        return tmp;
    }

    @Override
    public Brick[] createBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC) {
        CreationOfBricks CoB = new CreationOfBricks(drawArea, brickCnt, lineCnt, brickSizeRatio, typeA, typeB, typeC);
        return CoB.getTmp();
    }
}
