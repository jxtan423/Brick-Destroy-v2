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

public abstract class Wall {

    private final Rectangle area;

    Brick[] bricks;
    Ball ball;
    Player player;

    private static final int PLAYER_WIDTH = 150;
    private static final int PLAYER_HEIGHT = 10;

    private final Brick[][] levels;
    private int level = 0;

    private final Point startPoint;

    int brickCount;
    int ballCount = 3;
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
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
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

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
