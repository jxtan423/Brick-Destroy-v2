package App.Graphics.Frame.InGame.View;

import App.Graphics.Frame.GameFrame;
import App.Graphics.Frame.InGame.Model.Wall;
import App.Graphics.Frame.InGame.ShowScore;
import App.Graphics.Frame.InGame.SubmitScore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This abstract class is to create game
 * along with pause menu, timer, score and
 * the frame.
 */

public abstract class Game extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    PauseMenu pM;
    SubmitScore submit;
    ShowScore show;
    String message;
    String tab;

    Timer gameTimer;
    Timer timer;

    boolean showPauseMenu;
    private final int DEF_WIDTH;
    private final int DEF_HEIGHT;

    /**
     * This constructor is to create the pause menu,
     * the pop up score and fill in score.
     * The pause Menu will be invisible since the
     * boolean value is assigned by false.
     * Numerous methods will be executed.
     *
     * @param owner Control GameFrame method whenever event is captured
     * @param DEF_WIDTH The width of the frame
     * @param DEF_HEIGHT The height of the frame
     * @param filename The name of the file with directory
     */

    public Game(GameFrame owner, int DEF_WIDTH, int DEF_HEIGHT, String filename) {
        this.DEF_WIDTH = DEF_WIDTH;
        this.DEF_HEIGHT = DEF_HEIGHT;
        pM = new PauseMenu(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        show = new ShowScore(owner);
        submit = new SubmitScore(owner, filename);
        showPauseMenu = false;
        tab = "Press Tab to start game";
        message = "";
        createWall();
        createTimer();
        createGame();
        initialize();
    }

    /**
     * This method is to check whether the number
     * of ball lost.
     * If it is 3 times, then the game is over
     * and the user has to restart whereas if less
     * than 3 times, then the game continues when user
     * starts the game
     */

    public void checkAmountOfBallLost(Wall wall) {
        if (wall.ballEnd()) {
            message = "GAME OVER";
            reset();
        }
        wall.ballReset();
        gameTimer.stop();
        timer.stop();
    }

    /**
     * Does nothing in this class
     * and will be implemented on its
     * child class.
     * This is for creating the wall.
     */

    public abstract void createWall();

    /**
     * Does nothing in this class
     * and will be implemented on its
     * child class.
     * This is for creating the timer.
     */

    public abstract void createTimer();

    /**
     * Does nothing in this class
     * and will be implemented on its
     * child class.
     * This is for starting the game.
     */

    public abstract void createGame();

    /**
     * Does nothing in this class
     * and will be implemented on its
     * child class.
     * This is for resetting the game
     * or level.
     */

    public abstract void reset();

    /**
     * This method is to set the size
     * of the frame and request focus.
     * It will be added some listeners
     */

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * This method will execute the
     * clear method.
     *
     * @param g The graphics context on which to paint
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
    }

    /**
     * The background of the game will be
     * set as white colour.
     *
     * @param g2d The graphics context on which to paint in 2D form
     */

    private void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }

    /**
     * This method does nothing.
     *
     * @param e key activity made bu user
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e mouse activity made bu user
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * This method gets the coordinate of the mouse.
     * Whenever the mouse points to any buttons,
     * the cursor will turn to hand cursor.
     * Else, the cursor will turn to default mode.
     *
     * @param mouseEvent Mouse activity made bu user
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (pM.getExitBtn() != null && showPauseMenu) {
            if (pM.getExitBtn().contains(p) || pM.getContinueBtn().contains(p) || pM.getRestartBtn().contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        } else
            this.setCursor(Cursor.getDefaultCursor());
    }

    /**
     * This method does nothing.
     *
     * @param e mouse activity made bu user
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e mouse activity made bu user
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e mouse activity made bu user
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e mouse activity made bu user
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * This method is triggered whenever
     * the window has lost focus.
     * The game will immediately stop
     * and a massage will be displayed.
     * It repaints the frame.
     */
    public void onLostFocus() {
        gameTimer.stop();
        timer.stop();
        message = "Focus lost";
        repaint();
    }
}