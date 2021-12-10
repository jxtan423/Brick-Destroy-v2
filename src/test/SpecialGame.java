package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SpecialGame extends Game {

    private static final int DEF_WIDTH = 897;
    private static final int DEF_HEIGHT = 675;
    private static final String filename = "special.txt";

    private final JFrame frame;
    private final GameFrame owner;
    private SpecialWall wall;

    private Timer gameTimer;
    private Timer timer;
    private Timer celebration;

    private int second;
    private final int CR7Score;
    private boolean isCR7Time;
    private boolean is7Pressed;

    public SpecialGame(GameFrame owner) {
        super(owner, DEF_WIDTH, DEF_HEIGHT, filename);
        frame = new GIF().getGIFFrame();
        this.owner = owner;
        isCR7Time = false;
        is7Pressed = false;
        second = 90;
        CR7Score = 1;
    }

    @Override
    public void createWall() {
        wall = new SpecialWall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 117, 9, 3, new Point(450, 655));
    }

    @Override
    public void createTimer() {
        timer = new Timer(1000, e -> {
            second--;
            if(second == 0)
                gameOver();
        });
    }

    @Override
    public void createGame() {
        wall.ballReset();
        wall.nextLevel();
        gameTimer = new Timer(10, e -> {
            wall.move();
            wall.findImpacts();
            message = String.format("Balls : %d", wall.ballCount);
            if (wall.isBallLost())
                checkAmountOfBallLost();
            else if(wall.isDone())
                second = 0;
            if(wall.getScore() == CR7Score)
                isCR7Time = !is7Pressed;
            repaint();
        });
    }

    private void checkAmountOfBallLost() {
        if (wall.ballEnd()) {
            tab = "GAME OVER";
            reset();
        }
        wall.ballReset();
        gameTimer.stop();
        timer.stop();
    }

    @Override
    public void reset() {
        wall.wallReset();
        second = 90;
    }

    private void gameOver() {
        gameTimer.stop();
        timer.stop();
        show.setScore(wall.getScore());
        submit.setHighScore(wall.getScore());
        submit.scoreVisible();
        show.scoreVisible();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,897,675);
        g2d.setColor(Color.WHITE);
        g2d.drawString(tab,385,420);
        g2d.drawString(message, 800,665);

        wall.player.paint(g);
        wall.ball.paint(g);

        for(Brick b : wall.bricks)
            if(!b.isBroken())
                b.paint(g);

        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.drawString(String.valueOf(wall.score), 800, 650);
        g2d.drawString(String.valueOf(second), 415,320);

        if(isCR7Time) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.ITALIC, 20));
            String ronaldoMode = "Press 7 to activate Ronaldo Mode";
            g2d.drawString(ronaldoMode, 300, 400);
        }

        if (showPauseMenu)
            pM.paint(g);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.player.movRight();
                break;
            case KeyEvent.VK_7:
                if(isCR7Time && !is7Pressed) {
                    owner.game();
                    gameTimer.stop();
                    timer.stop();
                    frame.setVisible(true);
                    wall.setCR7Ball(wall.ball.getPosition());
                    celebration = new Timer(2620, ex -> {
                        owner.game();
                        frame.dispose();
                        gameTimer.start();
                        timer.start();
                        celebration.stop();
                    });
                    celebration.start();
                    is7Pressed = true;
                }
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
                        gameTimer.stop();
                        timer.stop();
                    } else {
                        gameTimer.start();
                        timer.start();
                        tab = "";
                    }
                break;
            default:
                wall.player.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (showPauseMenu) {
            if (pM.getContinueBtn().contains(p)) {
                showPauseMenu = false;
                repaint();
            } else if (pM.getRestartBtn().contains(p)) {
                tab = "Game restarts";
                wall.ballReset();
                reset();
                showPauseMenu = false;
                isCR7Time = false;
                is7Pressed = false;
                wall.setScore(0);
                wall.setRubberBall(wall.ball.getPosition());
                wall.ballReset();
                repaint();
            } else if (pM.getExitBtn().contains(p)) {
                System.exit(0);
            }
        }
    }
}
