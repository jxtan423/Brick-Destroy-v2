package App.Graphics.Frame.InGame.Model;

import App.Graphics.Frame.InGame.View.Brick;
import App.Graphics.Frame.InGame.View.ImpactDirection;
import App.Graphics.Frame.InGame.View.RonaldoBall;
import App.Graphics.Frame.InGame.View.RubberBall;

import java.awt.*;
import java.awt.geom.Point2D;

public class SpecialWall extends Wall {

    private static final int LEVELS_COUNT = 1;
    private static final int GREEN = 4;
    private static final int YELLOW = 5;
    private static final int RED = 6;

    private boolean isCR7Time;

    public SpecialWall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        super(drawArea, brickCount, lineCount, brickDimensionRatio, ballPos);
        super.setBall(new RubberBall(ballPos));
    }

    @Override
    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, GREEN, YELLOW, RED);
        return tmp;
    }

    @Override
    public Brick[] createBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC) {
        CreationOfBricks CoB = new CreationOfBricks(drawArea, brickCnt, lineCnt, brickSizeRatio, typeA, typeB, typeC);
        return CoB.getTmp();
    }

    public void setCR7Ball(Point2D point) {
        super.setBall(new RonaldoBall(point));
        super.getBall().setSpeed(2, 2);
        isCR7Time = true;
    }

    public void setRubberBall(Point2D point) {
        super.setBall(new RubberBall(point));
        isCR7Time = false;
    }

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

