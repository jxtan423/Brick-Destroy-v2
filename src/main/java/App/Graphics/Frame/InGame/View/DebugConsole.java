package App.Graphics.Frame.InGame.View;

import App.Graphics.Frame.InGame.Model.DebugPanel;
import App.Graphics.Frame.InGame.Model.Wall;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class inherits JDialog and implements WindowListener.
 * This class is to show a frame about debug console.
 * This class will be applied on normal game ONLY.
 */

public class DebugConsole extends JDialog implements WindowListener {

    private static final String TITLE = "Debug Console";
    private final JFrame owner;
    private final DebugPanel debugPanel;
    private final NormalGame normalGame;
    private final Wall wall;

    /**
     * This constructor is to assigned variables from
     * the parameters and execute the initialize method.
     * DebugPanel is instantiated and is added into the
     * JDialog.
     * The debug console will fit the frame.
     *
     * @param owner Control GameFrame method whenever event is captured
     * @param wall The wall
     * @param normalGame The normal Game
     */

    public DebugConsole(JFrame owner, Wall wall, NormalGame normalGame) {
        this.wall = wall;
        this.owner = owner;
        this.normalGame = normalGame;
        initialize();
        debugPanel = new DebugPanel(wall);
        this.add(debugPanel, BorderLayout.CENTER);
        this.pack();
    }

    /**
     * This method is to set the
     * debug console.
     * The name of the frame is set
     * as well as the layout.
     * This method also add a window listener
     * to this class.
     */

    private void initialize() {
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * This method is to set the
     * location of the debug console
     * at the middle of the normal game
     * frame.
     */

    private void setLocation() {
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x, y);
    }

    /**
     * This method does nothing.
     *
     * @param windowEvent window event that performed by user
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {
    }

    /**
     * This method will repaint the normal game
     * frame whenever the user close the
     * debug console.
     *
     * @param windowEvent window event that performed by user
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        normalGame.repaint();
    }

    /**
     * This method does nothing.
     *
     * @param windowEvent window event that performed by user
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {
    }

    /**
     * This method does nothing.
     *
     * @param windowEvent window event that performed by user
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {
    }

    /**
     * This method does nothing.
     *
     * @param windowEvent window event that performed by user
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {
    }

    /**
     * This method will set the location of the
     * debug console, and get the speed of the ball
     * from wall class and pass it to debug panel to
     * perform its functionality.
     *
     * @param windowEvent window event that performed by user
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.getBall();
        debugPanel.setValues(b.getSpeedX(), b.getSpeedY());
    }

    /**
     * This method does nothing.
     *
     * @param windowEvent window event that performed by user
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {
    }
}
