package App.Graphics.Frame.InGame.View;

import App.Graphics.Frame.GameFrame;
import App.Graphics.Frame.InGame.Model.NormalWall;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * This class inherits Game class to obtain
 * its parents' attributes in terms of
 * pause menu, timer and score.
 * This class will display another feature
 * which is the debug console.
 * The fonts and coordinates are set.
 */

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

    /**
     * This constructor calls its parents to obtain
     * the parents' attributes by passing the required
     * variables to the parent class.
     * Second and minutes will be initialized as 0.
     * Debug console object is created along with the
     * text Font.
     *
     * @param owner Control GameFrame method whenever event is captured
     */

    public NormalGame(GameFrame owner) {
        super(owner, DEF_WIDTH, DEF_HEIGHT, filename);
        this.owner = owner;
        second = 0;
        min = 0;
        textFont = new Font("Arial", Font.BOLD, 18);
        debugConsole = new DebugConsole(owner, wall, this);
    }

    /**
     * This method is to create a Normal Wall for normal
     * game with required variables pass to the
     * NormalWall constructor.
     */
    @Override
    public void createWall() {
        wall = new NormalWall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 30, 3, 3, new Point(300, 430));
    }

    /**
     * This method is to create a timer
     * during the game.
     * This timer is to capture the
     * amount of time used by the user
     * to complete the level.
     * If the second reaches 60, it will
     * be initialized as 0 along with add
     * 1 to the minutes, same like how
     * time works in the real life.
     */
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

    /**
     * This method is to start the normal game.
     * Before the game starts, the ball and player
     * position will be set at initial coordinates.
     * It initializes the first level as well.
     * When the game starts, this method will execute
     * the findImpacts method continuously and check
     * whether the ball has lost or the bricks are
     * completely destroyed.
     * This method will continuously repaint and update
     * the game.
     */
    @Override
    public void createGame() {
        wall.ballReset();
        wall.nextLevel();
        gameTimer = new Timer(10, e -> {
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d", wall.getBrickCount(), wall.getBallCount());
            if (wall.isBallLost())
                super.checkAmountOfBallLost(wall);
            else if (wall.isDone())
                checkLevel();
            repaint();
        });
    }

    /**
     * This method is to check whether there's
     * any remaining level in the brick array.
     * If yes, the user proceed to next level.
     * If no, the user will be directed to the
     * pop up to fill in the name.
     * Every single level, there will be a
     * pop up score after the user completes
     * the level.
     */

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

    /**
     * This method is to reset the
     * timer as well as the wall.
     */
    @Override
    public void reset() {
        ddSecond = "00";
        ddMin = "00";
        min = 0;
        second = 0;
        wall.wallReset();
    }

    /**
     * This method is to draw the String for the message
     * and tab.
     * The ball, the player as well as the bricks are drawn.
     * The timer is drawn in a specific format.
     * The pause menu will be shown when the boolean
     * variable is true.
     *
     * @param g The graphics context on which to paint
     */

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

    /**
     * This method is to capture the key
     * pressed by the user.
     * Each key will performed its respective
     * function whenever the key is pressed.
     *
     * @param keyEvent key activity made bu user
     */
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

    /**
     * This method is to capture when user
     * release the key.
     * The player will remain its respective position
     * no matter which key is released.
     *
     * @param keyEvent key activity made bu user
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.getPlayer().stop();
    }

    /**
     * This method gets the coordinate of the mouse.
     * Whenever the mouse clicks on either of the buttons,
     * the respective buttons will perform the respective
     * function if and only if the showPauseMenu is true
     *
     * @param mouseEvent Mouse activity made bu user
     */

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