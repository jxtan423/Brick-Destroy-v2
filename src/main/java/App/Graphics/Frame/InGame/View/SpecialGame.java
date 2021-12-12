package App.Graphics.Frame.InGame.View;

import App.Graphics.Frame.GameFrame;
import App.Graphics.Frame.InGame.Model.SpecialWall;
import App.Graphics.GIF;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class inherits Game class to obtain
 * its parents' attributes in terms of
 * pause menu, timer and score.
 * This class will display another feature
 * which is the CR7 mode which is the reward.
 */

public class SpecialGame extends Game {

    private static final int DEF_WIDTH = 897;
    private static final int DEF_HEIGHT = 675;
    private static final String filename = "src/main/resources/special.txt";

    private final JFrame frame;
    private final GameFrame owner;
    private SpecialWall wall;

    private Timer celebration;

    private int second;
    private final int CR7Score;
    private boolean isCR7Time;
    private boolean is7Pressed;

    /**
     * This constructor calls its parents to obtain
     * the parents' attributes by passing the required
     * variables to the parent class.
     * The boolean variables will be assigned as false.
     * The second will be initialized as 90, which mean
     * the timer start at 90 seconds.
     * The CR7 score is initialized.
     *
     * @param owner Control GameFrame method whenever event is captured
     */

    public SpecialGame(GameFrame owner) {
        super(owner, DEF_WIDTH, DEF_HEIGHT, filename);
        frame = new GIF().getGIFFrame();
        this.owner = owner;
        isCR7Time = false;
        is7Pressed = false;
        second = 90;
        CR7Score = 10;
    }

    /**
     * This method is to create a Special Wall for special
     * game with required variables pass to the
     * SpecialWall constructor.
     */
    @Override
    public void createWall() {
        wall = new SpecialWall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 117, 9, 3, new Point(450, 655));
    }

    /**
     * This method is to create a timer
     * during the game.
     * This is a countdown timer where
     * user is given 90 seconds to
     * play the game.
     *
     */
    @Override
    public void createTimer() {
        timer = new Timer(1000, e -> {
            second--;
            if(second == 0)
                gameOver();
        });
    }

    /**
     * This method is to start the special game.
     * Before the game starts, the ball and player
     * position will be set at initial coordinates.
     * It initializes the first level as well.
     * When the game starts, this method will execute
     * the findImpacts method continuously and check
     * whether the ball has lost or the bricks are
     * completely destroyed.
     * It performs the boolean variable to change its
     * values when certain condition made.
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
            message = String.format("Balls : %d", wall.getBallCount());
            if (wall.isBallLost())
                super.checkAmountOfBallLost(wall);
            else if(wall.isDone())
                second = 0;
            if(wall.getScore() == CR7Score)
                isCR7Time = !is7Pressed;
            repaint();
        });
    }

    /**
     * This method is to reset the
     * timer as well as the wall.
     */
    @Override
    public void reset() {
        wall.wallReset();
        second = 90;
    }

    /**
     * This method is to display the score
     * on user's screen when the game
     * is finished.
     * The timer will stop.
     */

    private void gameOver() {
        gameTimer.stop();
        timer.stop();
        show.setScore(wall.getScore());
        submit.setHighScore(wall.getScore());
        submit.scoreVisible();
        show.scoreVisible();
    }

    /**
     * This method is to draw the String for the message
     * and tab.
     * The background of the frame is set to black colour.
     * The ball, the player as well as the bricks are drawn.
     * The timer is drawn in a specific format.
     * An alert message with specific fonts
     * and coordinate will be drawn when the boolean
     * variable is true to notify the user.
     * The pause menu will be shown when the boolean
     * variable is true.
     *
     * @param g The graphics context on which to paint
     */

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,897,675);
        g2d.setColor(Color.WHITE);
        g2d.drawString(tab,385,420);
        g2d.drawString(message, 800,665);

        wall.getPlayer().paint(g);
        wall.getBall().paint(g);

        for(Brick b : wall.getBricks())
            if(!b.isBroken())
                b.paint(g);

        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.drawString(String.valueOf(wall.getScore()), 800, 650);
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

    /**
     * This method is to capture the key
     * pressed by the user.
     * Each key will performed its respective
     * function whenever the key is pressed.
     *
     * @param e key activity made bu user
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                wall.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.getPlayer().movRight();
                break;
            case KeyEvent.VK_7:
                if(isCR7Time && !is7Pressed) {
                    owner.game();
                    gameTimer.stop();
                    timer.stop();
                    frame.setVisible(true);
                    wall.setCR7Ball(wall.getBall().getPosition());
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
     * @param e Mouse activity made bu user
     */
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
                wall.setRubberBall(wall.getBall().getPosition());
                wall.ballReset();
                repaint();
            } else if (pM.getExitBtn().contains(p)) {
                System.exit(0);
            }
        }
    }
}
