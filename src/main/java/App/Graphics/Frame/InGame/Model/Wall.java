package App.Graphics.Frame.InGame.Model;

import App.Graphics.Frame.InGame.View.Ball;
import App.Graphics.Frame.InGame.View.Brick;
import App.Graphics.Frame.InGame.View.ImpactDirection;
import App.Graphics.Frame.InGame.View.Player;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This abstract class is to create a
 * wall to the frame which includes player,
 * ball, bricks, border and ceiling as well
 * as level(s).
 */

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

    /**
     * This constructor is to initialize the ball position
     * as well as player.
     * The levels are created by executing the make levels method.
     * The player width and height will be initialized and
     * the score for the start of the game is initialized as 0.
     *
     * @param drawArea The area of the frame
     * @param brickCount The total number of the bricks
     * @param lineCount The rows of the bricks
     * @param brickDimensionRatio The ratio of the bricks
     * @param ballPos The initial coordinates for the ball
     */

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        this.startPoint = new Point(ballPos);
        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        player = new Player((Point) ballPos.clone(), PLAYER_WIDTH, PLAYER_HEIGHT, drawArea);
        area = drawArea;
        score = 0;
    }

    /**
     * This abstract method will be implemented through child class
     * to make levels (For game extension in the future).
     *
     * @param drawArea The area of the frame
     * @param brickCount The total number of the bricks
     * @param lineCount The rows of the bricks
     * @param brickDimensionRatio The ratio of the bricks
     * @return The brick 2D array along with levels and bricks
     */

    public abstract Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio);

    /**
     * This abstract method will be implemented through child class
     * to create different types of bricks (For game extension in the future).
     *
     * @param drawArea The area of the frame
     * @param brickCnt The total number of the bricks
     * @param lineCnt The rows of the bricks
     * @param brickSizeRatio The ratio of the bricks
     * @param typeA Type A bricks
     * @param typeB Type B bricks
     * @param typeC Type C bricks
     * @return The array of bricks
     */

    public abstract Brick[] createBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC);

    /**
     * This method will execute
     * player and ball move method
     * in their respective class' method.
     */

    public void move() {
        player.move();
        ball.move();
    }

    /**
     * This method is to capture impact from the ball
     * whenever the ball collides with anything.
     * If capture any collusion, there will be respective
     * function to be executed.
     */

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

    /**
     * @return The score that the user gained
     */

    public int getScore() {
        return this.score;
    }

    /**
     * Set the score.
     * @param score The score that the user gained
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * This method is to find whether the user has an impact
     * with any bricks.
     * If yes, the direction of ball collides with the bricks will
     * be captured and further implementation will be executed.
     * The score will increase by 1 whenever there's a collusion
     * between the ball and the bricks (It is applicable for both
     * normal and special game, but currently is for special game use).
     *
     * @return The boolean value to identify whether the ball collides any bricks
     */

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

    /**
     * This method will capture the ball position when the game starts.
     * This method returns true whenever the ball collides with the
     * border of the frame no matter is left or right border.
     *
     * @return The boolean value to identify whether the ball collides the border of the frame
     */

    public boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * This method captures the ball position.
     * This method returns true when it exceeded the
     * frame ceiling.
     *
     * @return The boolean value to identify whether the ball collides the ceiling of the frame
     */

    public boolean impactCeiling() {
        return ball.getPosition().getY() < area.getY();
    }

    /**
     * This method captures the ball position.
     * This method returns true when it exceeded the
     * frame bottom.
     *
     * @return The boolean value to identify whether the ball collides the bottom of the frame
     */

    public boolean ballDrop() {
        return ball.getPosition().getY() > area.getY() + area.getHeight();
    }

    /**
     * This method is to identify Whether the
     * ball has drop to the bottom or not.
     *
     * @return The boolean value on whether the ball has dropped or not
     */

    public boolean isBallLost() {
        return ballLost;
    }

    /**
     * This method reset the ball position to
     * the initial position as well as the player.
     * The ball speed will be reset by random
     * number generator.
     */

    public void ballReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        Random rnd = new Random();
        int speedX = rnd.nextInt(2) + 2;
        int speedY = -rnd.nextInt(2) - 2;
        ball.setSpeed(speedX, speedY);
        ballLost = false;
    }

    /**
     * This method reset the bricks no matter
     * the bricks have been destroyed or not
     * to the default number of bricks and the
     * lives of the ball has been reset back
     * as well.
     */

    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * @return The boolean value where the ball loses its whole lives
     */

    public boolean ballEnd() {
        return ballCount == 0;
    }

    /**
     * @return The boolean value where the bricks are completely destroy
     */

    public boolean isDone() {
        return brickCount == 0;
    }

    /**
     * This method is to proceed to another level
     * and reset the brick count to its default
     * value.
     */

    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * @return The boolean value where identifying whether
     * the current index exceeded the total length of an array
     */

    public boolean hasLevel() {
        return level < levels.length;
    }

    /**
     * Set the speed of ball in in terms of x coordinates.
     *
     * @param s The speed of ball for x coordinates
     */

    public void setBallXSpeed(int s) {
        ball.setXSpeed(s);
    }

    /**
     * Set the speed of ball in in terms of y coordinates.
     *
     * @param s The speed of ball for y coordinates
     */

    public void setBallYSpeed(int s) {
        ball.setYSpeed(s);
    }

    /**
     * set the ball count to 3 (default value).
     */

    public void resetBallCount() {
        ballCount = 3;
    }

    /**
     * @return The respective type of ball
     */

    public Ball getBall() {
        return ball;
    }

    /**
     * Set the types of ball.
     *
     * @param ball The respective type of ball
     */

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * @return The array of the bricks
     */

    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * @return The player
     */

    public Player getPlayer() {
        return player;
    }

    /**
     * @return The current number of bricks left
     */

    public int getBrickCount() {
        return brickCount;
    }

    /**
     * @return The current number of the ball lives has
     */

    public int getBallCount() {
        return ballCount;
    }
}