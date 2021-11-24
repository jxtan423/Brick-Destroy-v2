/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {

    private static final int LEVELS_COUNT = 4;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private Rectangle area;

    Brick[] bricks;
    Ball ball;
    Player player;

    private static final int PLAYER_WIDTH = 150;
    private static final int PLAYER_HEIGHT = 10;

    private Brick[][] levels;
    private int level = 0;

    private Point startPoint;
    private int brickCount;
    private int ballCount = 3;
    private boolean ballLost = false;

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);

        player = new Player((Point) ballPos.clone(), PLAYER_WIDTH, PLAYER_HEIGHT, drawArea);

        makeBall(ballPos);

        ballReset();

        area = drawArea;
    }

    private Brick[] createBricks(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */

        brickCnt = AdjustBrickCount(brickCnt,lineCnt);

        int brickOnLine = CountBricksOnSingleLine(brickCnt,lineCnt);

        double brickLen = CalculateBrickLen(brickOnLine,drawArea);
        double brickHgt = CalculateBrickHgt(brickLen, brickSizeRatio);

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        double y;
        double x;

        for (i = 0; i < brickCnt; i++) {
            int line = identifyLine(i, brickOnLine);
            if (ReachMaxLine(line, lineCnt))
                break;
            int posX = identifyPositionX(i , brickOnLine);
            x = createBrickOnX(posX, brickLen, line);
            y = createBrickOnY(line, brickHgt);
            p.setLocation(x, y);

            if(haveTwoTypesOfBrick(typeA, typeB)) {
                boolean isTypeA = identifySequenceOfBricks(brickOnLine, line, posX, i);
                if(isTypeA) {
                    tmp[i] = makeSpecificBrick(p, brickSize, typeA);
                }
                else
                    tmp[i] = makeSpecificBrick(p, brickSize, typeB);
            }
            else
                tmp[i] = makeSpecificBrick(p, brickSize, typeA);

        }

        for (y = brickHgt; i < tmp.length; i++, y += NextOddLine(brickHgt) ) {
            x = identifyLastBrickOnX(brickOnLine, brickLen);
            p.setLocation(x, y);
            tmp[i] = makeSpecificBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    private boolean ReachMaxLine(int line, int lineCnt) {
        return line == lineCnt;
    }

    private int AdjustBrickCount(int brickCnt, int lineCnt){
        return (brickCnt - (brickCnt % lineCnt)) + lineCnt / 2 ;
    }

    private int CountBricksOnSingleLine(int brickCnt, int lineCnt){
        return brickCnt / lineCnt;
    }

    private double CalculateBrickLen(int brickOnLine, Rectangle drawArea){
        return drawArea.getWidth() / brickOnLine;
    }

    private double CalculateBrickHgt(double brickLen, double brickSizeRatio){
        return  brickLen / brickSizeRatio;
    }

    private int identifyLine(int i, int brickOnLine){
        return i / brickOnLine;
    }

    private int identifyPositionX(int i, int brickOnLine){
        return i % brickOnLine;
    }

    private double createBrickOnY(int line, double brickHgt) {
        return line * brickHgt;
    }

    private double createBrickOnX(int posX, double brickLen, int line) {
        double x = posX * brickLen;
        return (line % 2 == 0) ? x : (x - (brickLen / 2));
    }

    private boolean haveTwoTypesOfBrick(int typeA, int typeB){
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

    private void makeBall(Point2D ballPos) {
        ball = new RubberBall(ballPos);
    }

    private Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY,0);
        tmp[1] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = createBricks(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        return tmp;
    }

    public void move() {
        player.move();
        ball.move();
    }

    public void findImpacts() {
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
        } else if (impactBorder()) {
            ball.reverseX();
        } else if (impactCeiling()) {
            ball.reverseY();
        } else if (ballDrop()) {
            ballCount--;
            ballLost = true;
        }
    }

    private boolean impactWall() {
        for (Brick b : bricks) {
            //Vertical Impact
            //Horizontal Impact
            switch (b.findImpact(ball)) {
                case Brick.UP_IMPACT -> {
                    ball.reverseY();
                    return b.setImpact(ball.down, Brick.Crack.UP);
                }
                case Brick.DOWN_IMPACT -> {
                    ball.reverseY();
                    return b.setImpact(ball.up, Brick.Crack.DOWN);
                }
                case Brick.LEFT_IMPACT -> {
                    ball.reverseX();
                    return b.setImpact(ball.right, Brick.Crack.RIGHT);
                }
                case Brick.RIGHT_IMPACT -> {
                    ball.reverseX();
                    return b.setImpact(ball.left, Brick.Crack.LEFT);
                }
            }
        }
        return false;
    }

    private boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    private boolean impactCeiling(){
        return ball.getPosition().getY() < area.getY();
    }

    private boolean ballDrop(){
        return ball.getPosition().getY() > area.getY() + area.getHeight();
    }

    public int getBrickCount() {
        return brickCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void ballReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        Random rnd = new Random();
        int speedX, speedY;
        do {
            speedX = rnd.nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -rnd.nextInt(3);
        } while (speedY == 0);

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

    private Brick makeSpecificBrick(Point point, Dimension size, int type) {
        Brick out;
        switch (type) {
            case CLAY:
                out = new ClayBrick(point, size);
                break;
            case STEEL:
                out = new SteelBrick(point, size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        }
        return out;
    }
}
