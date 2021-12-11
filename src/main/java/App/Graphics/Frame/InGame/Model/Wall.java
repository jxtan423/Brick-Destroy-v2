package App.Graphics.Frame.InGame.Model;

import App.Graphics.Frame.InGame.View.Ball;
import App.Graphics.Frame.InGame.View.Brick;
import App.Graphics.Frame.InGame.View.ImpactDirection;
import App.Graphics.Frame.InGame.View.Player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public abstract class Wall {

    private final Rectangle area;

    private Brick[] bricks;
    private Ball ball;

    private final Player player;

    private static final int PLAYER_WIDTH = 150;
    private static final int PLAYER_HEIGHT = 10;

    private final Brick[][] levels;
    private int level = 0;

    private final Point startPoint;

    private int brickCount;
    private int ballCount = 3;
    boolean ballLost = false;

    int score;

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        this.startPoint = new Point(ballPos);
        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        player = new Player((Point) ballPos.clone(), PLAYER_WIDTH, PLAYER_HEIGHT, drawArea);
        area = drawArea;
        score = 0;
    }

    public abstract Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio);

    public abstract Brick[] createBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC);

    public void move() {
        player.move();
        ball.move();
    }

    public void findImpacts() {
        if (player.impact(ball))
            ball.reverseY();
        else if (impactWall())
            brickCount--;
        else if (impactBorder())
            ball.reverseX();
        else if (impactCeiling())
            ball.reverseY();
        else if (ballDrop()) {
            ballCount--;
            ballLost = true;
        }
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean impactWall() {
        int IMPACT_NOT_FOUND = 4;
        for (Brick b : bricks) {
            if(b.findImpact(ball) < IMPACT_NOT_FOUND ) {
                score++;
                return ImpactDirection.values()[b.findImpact(ball)].setImpact(b, ball, true);
            }
        }
        return false;
    }

    public boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    public boolean impactCeiling() {
        return ball.getPosition().getY() < area.getY();
    }

    public boolean ballDrop() {
        return ball.getPosition().getY() > area.getY() + area.getHeight();
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void ballReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        Random rnd = new Random();
        int speedX = rnd.nextInt(2) + 2;
        int speedY = -rnd.nextInt(2) - 2;
        ball.setSpeed(speedX, speedY);
        ballLost = false;
    }

    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd() {
        return ballCount == 0;
    }

    public boolean isDone() {
        return brickCount == 0;
    }

    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel() {
        return level < levels.length;
    }

    public void setBallXSpeed(int s) {
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s) {
        ball.setYSpeed(s);
    }

    public void resetBallCount() {
        ballCount = 3;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public int getBallCount() {
        return ballCount;
    }
}