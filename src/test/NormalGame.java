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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

public class NormalGame extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;
    private Timer timer;

    int second,min;
    String ddSecond = "00",ddMin = "00";

    DecimalFormat format = new DecimalFormat("00");
    private final NormalWall wall;

    private String message;

    private boolean showPauseMenu;

    private final Font textFont;

    private final SubmitScore submit;
    private final ShowScore show;

    private final DebugConsole debugConsole;

    private final GameFrame owner;

    private final PauseMenu pM;

    private final Ball ball;

    public NormalGame(GameFrame owner) {
        super();
        pM = new PauseMenu(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.owner = owner;
        showPauseMenu = false;
        second = 0;
        min = 0;
        textFont = new Font("Arial", Font.BOLD, 18);
        submit = new SubmitScore(owner);
        show = new ShowScore(owner);
        message = "Press Tab to start game";
        wall = new NormalWall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 1, 1, 3, new Point(300, 430));
        ball = wall.getBall();
        debugConsole = new DebugConsole(owner, wall, this);
        startTimer();
        startGame();
        this.initialize();
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            second++;
            ddSecond = format.format(second);
            ddMin = format.format(min);

            if(second == 60) {
                min++;
                second = 0;
            }
        });
    }

    private void startGame() {
        wall.ballReset();
        //initialize the first level
        wall.nextLevel();
        gameTimer = new Timer(10, e -> {
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d", wall.brickCount, wall.ballCount);
            if (wall.isBallLost()) {
                if (wall.ballEnd()) {
                    wall.wallReset();
                    message = "GAME OVER";
                    timerReset();
                }
                wall.ballReset();
                gameTimer.stop();
                timer.stop();
            } else if (wall.isDone()) {
                if (!wall.hasLevel()) {
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    timer.stop();
                    show.setScore(min,second);
                    submit.setHighScore(show.getHighScore());
                    submit.scoreVisible();
                }
                else {
                    message = "Next Level";
                    gameTimer.stop();
                    timer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                    show.setScore(min,second);
                    this.owner.game();
                    timerReset();
                }
                show.scoreVisible();
            }
            repaint();
        });
    }

    private void timerReset() {
        ddSecond = "00";
        ddMin = "00";
        min = 0;
        second = 0;
    }

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
        g2d.setColor(Color.BLUE);
        g2d.drawString(message, 230, 225);

        ball.paint(g);
        wall.player.paint(g);
        for (Brick b : wall.bricks)
            if (!b.isBroken())
                b.paint(g);
        g2d.setFont(textFont);
        g2d.drawString(ddMin + ":" + ddSecond, 520, 45);
        if (showPauseMenu)
            pM.paint(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.player.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                timer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if (!showPauseMenu)
                    if (gameTimer.isRunning()) {
                        timer.stop();
                        gameTimer.stop();
                    }  else {
                        gameTimer.start();
                        timer.start();
                    }
                break;
            case KeyEvent.VK_F1:
                if (keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.player.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (showPauseMenu) {
            if (pM.getContinueBtn().contains(p)) {
                showPauseMenu = false;
                repaint();
            } else if (pM.getRestartBtn().contains(p)) {
                message = "Game restarts";
                wall.ballReset();
                wall.wallReset();
                timerReset();
                showPauseMenu = false;
                repaint();
            } else if (pM.getExitBtn().contains(p)) {
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (pM.getExitBtn() != null && showPauseMenu) {
            if (pM.getExitBtn().contains(p) || pM.getContinueBtn().contains(p) || pM.getRestartBtn().contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus() {
        gameTimer.stop();
        message = "Focus lost";
        repaint();
    }
}