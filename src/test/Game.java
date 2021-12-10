/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021 Tan Jian Xin
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
    }

    private void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

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

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void onLostFocus() {
        gameTimer.stop();
        timer.stop();
        message = "Focus lost";
        repaint();
    }
}