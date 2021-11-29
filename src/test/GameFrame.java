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
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to create a frame with different contents
 * on user's screen.
 * The user can interact with the window by keyboard or mouse.
 */

public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private SelectionGame selectionGame;
    private GameInfo infoPage;
    private HighScore highScore;

    private boolean gaming;

    /**
     * This is a constructor.
     * It creates the GameBoard object and HomeMenu object.
     * HomeMenu will be added to the frame and displayed on the user's screen first.
     * The frame border will not be displayed on user's screen.
     */

    public GameFrame() {
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        enableHomeMenu();
    }

    /**
     * This method set the window name to "Brick Destroy".
     * The frame will appear at exact coordinate on the user's screen.
     * The frame will be sized perfectly to ensure every content is at preferred size.
     * The game will be terminated once the user closes the window.
     */

    public void initialize() {
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    public void enableHomeMenu() {
        this.dispose();
        homeMenu = new HomeMenu(this);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
    }

    /**
     * The current window will be destroyed once this method is executed.
     * The HomeMenu will be removed from the user's screen.
     * The GameBoard will be shown on the user's screen along with frame border.
     */

    public void enableGameBoard() {
        this.dispose();
        this.remove(selectionGame);
        gameBoard = new GameBoard(this);
        this.add(gameBoard, BorderLayout.CENTER);
        this.setUndecorated(false);
        this.setResizable(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    public void enableSelectionGame(boolean fromHomeMenu) {
        if(fromHomeMenu)
            this.remove(homeMenu);
        selectionGame = new SelectionGame(this);
        this.add(selectionGame, BorderLayout.CENTER);
        initialize();
    }

    public void enableInfo() {
        this.remove(selectionGame);
        infoPage = new GameInfo(this);
        this.add(infoPage, BorderLayout.CENTER);
        initialize();
    }

    public void enableScore() throws FileNotFoundException {
        highScore = new HighScore(this);
    }

    /**
     * This method will get user's screen resolution.
     * The width and height will undergo calculation to get
     * a perfect frame coordinate.
     * The coordinate of the frame will be set.
     */

    private void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    /**
     * Invoked when the Window is set to be the focused Window, which means
     * that the Window, or one of its subcomponents, will receive keyboard
     * events.
     * The user starts the game.
     *
     * @param windowEvent the event to be processed
     */

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * Invoked when the Window is no longer the focused Window, which means
     * that keyboard events will no longer be delivered to the Window or any of
     * its subcomponents.
     * onLostFocus will be executed in GameBoard class if the user is playing.
     *
     * @param windowEvent the event to be processed
     */

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (gaming)
            gameBoard.onLostFocus();
    }
}
