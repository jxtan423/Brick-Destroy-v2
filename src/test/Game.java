package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Game extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    PauseMenu pM;
    SubmitScore submit;
    ShowScore show;
    String message;
    String tab;

    boolean showPauseMenu;
    private final int DEF_WIDTH;
    private final int DEF_HEIGHT;

    public Game(GameFrame owner, int DEF_WIDTH, int DEF_HEIGHT) {
        this.DEF_WIDTH = DEF_WIDTH;
        this.DEF_HEIGHT = DEF_HEIGHT;
        pM = new PauseMenu(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        show = new ShowScore(owner);
        submit = new SubmitScore(owner);
        showPauseMenu = false;
        tab = "Press Tab to start game";
        message = "";
        createWall();
        createTimer();
        createGame();
        initialize();
    }

    public abstract void createWall();

    public abstract void createTimer();

    public abstract void createGame();

    public abstract void reset();

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
}
