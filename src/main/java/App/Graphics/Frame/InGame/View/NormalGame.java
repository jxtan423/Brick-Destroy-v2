package App.Graphics.Frame.InGame.View;

import App.Graphics.Frame.GameFrame;
import App.Graphics.Frame.InGame.Model.NormalWall;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class NormalGame extends Game {

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final String filename = "src/main/resources/normal.txt";

    int second, min;
    String ddSecond = "00", ddMin = "00";
    DecimalFormat format = new DecimalFormat("00");

    private final Font textFont;
    private final DebugConsole debugConsole;
    private final GameFrame owner;
    private NormalWall wall;

    public NormalGame(GameFrame owner) {
        super(owner, DEF_WIDTH, DEF_HEIGHT, filename);
        this.owner = owner;
        second = 0;
        min = 0;
        textFont = new Font("Arial", Font.BOLD, 18);
        debugConsole = new DebugConsole(owner, wall, this);
    }

    @Override
    public void createWall() {
        wall = new NormalWall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 1, 1, 3, new Point(300, 430));
    }

    @Override
    public void createTimer() {
        timer = new Timer(1000, e -> {
            second++;
            ddSecond = format.format(second);
            ddMin = format.format(min);

            if (second == 60) {
                min++;
                second = 0;
            }
        });
    }

    @Override
    public void createGame() {
        wall.ballReset();
        wall.nextLevel();
        gameTimer = new Timer(10, e -> {
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d", wall.getBallCount(), wall.getBallCount());
            if (wall.isBallLost())
                checkAmountOfBallLost();
            else if (wall.isDone())
                checkLevel();
            repaint();
        });
    }

    private void checkAmountOfBallLost() {
        if (wall.ballEnd()) {
            message = "GAME OVER";
            reset();
        }
        wall.ballReset();
        gameTimer.stop();
        timer.stop();
    }

    private void checkLevel() {
        if (!wall.hasLevel()) {
            message = "ALL WALLS DESTROYED";
            gameTimer.stop();
            timer.stop();
            show.setScore(min, second);
            submit.setHighScore(show.getHighScore());
            submit.scoreVisible();
        } else {
            message = "NEXT LEVEL";
            gameTimer.stop();
            timer.stop();
            reset();
            wall.ballReset();
            wall.nextLevel();
            show.setScore(min, second);
            this.owner.game();
        }
        show.scoreVisible();
    }

    @Override
    public void reset() {
        ddSecond = "00";
        ddMin = "00";
        min = 0;
        second = 0;
        wall.wallReset();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE.darker());
        g2d.drawString(tab, 230, 225);
        g2d.drawString(message, 250, 225);

        wall.getBall().paint(g);
        wall.getPlayer().paint(g);

        for (Brick b : wall.getBricks())
            if (!b.isBroken())
                b.paint(g);

        g2d.setFont(textFont);
        g2d.drawString(ddMin + ":" + ddSecond, 540, 85);

        if (showPauseMenu)
            pM.paint(g);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                wall.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.getPlayer().movRight();
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
                    } else {
                        gameTimer.start();
                        timer.start();
                        tab = "";
                    }
                break;
            case KeyEvent.VK_F1:
                if (keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.getPlayer().stop();
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
                reset();
                showPauseMenu = false;
                repaint();
            } else if (pM.getExitBtn().contains(p)) {
                System.exit(0);
            }
        }
    }
}