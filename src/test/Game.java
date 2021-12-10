package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
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

public abstract class Game extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    PauseMenu pM;
    SubmitScore submit;
    ShowScore show;
    String message;
    String tab;

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
}
