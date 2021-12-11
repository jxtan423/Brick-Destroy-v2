package App.Graphics.Frame;/*
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


import App.Graphics.Frame.InGame.View.NormalGame;
import App.Graphics.Frame.Menu.PageVC.SelectionGame;
import App.Graphics.Frame.InGame.View.SpecialGame;
import App.Graphics.Frame.Menu.PageVC.GameInfo;
import App.Graphics.Frame.Menu.PageVC.HighScore;
import App.Graphics.Frame.Menu.PageVC.HomeMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

/**
 * This class is to create a frame with different contents
 * on user's screen.
 * The user can interact with the window by keyboard or mouse.
 */

public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private NormalGame normalGame;
    private HomeMenu homeMenu;
    private SelectionGame selectionGame;
    private SpecialGame specialGame;

    private boolean gaming;
    private boolean isNormalGame;

    /**
     * This is a constructor.
     * It creates the test.HomeMenu object and adds into the frame.
     * The frame border will not be displayed on user's screen.
     */

    public GameFrame() {
        super();
        gaming = false;
        this.setLayout(new BorderLayout());
        homeMenu = new HomeMenu(this);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(true);
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

    /**
     * This methods destroys the current user's window.
     * It removes test.NormalGame component if the parameter is true,
     * else it removes test.SpecialGame component.
     * test.HomeMenu object is created and added into the frame.
     * The frame border will not be displayed on user's screen.
     * @param isFromNormalGame To identify which frame the user came from
     */

    public void enableHomeMenu(Boolean isFromNormalGame) {
        this.dispose();
        if (isFromNormalGame)
            this.remove(normalGame);
        else
            this.remove(specialGame);
        homeMenu = new HomeMenu(this);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
    }

    /**
     * The current window will be destroyed once this method is executed.
     * The test.SelectionGame component will be removed from the user's screen.
     * The test.NormalGame will be shown on the user's screen along with frame border.
     * Window focus listener is added for the frame.
     */

    public void enableNormalGame() {
        this.dispose();
        this.remove(selectionGame);
        normalGame = new NormalGame(this);
        this.add(normalGame, BorderLayout.CENTER);
        this.setUndecorated(false);
        this.setResizable(false);
        initialize();
        isNormalGame = true;
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * This method destroys current window,
     * remove test.SelectionGame component,
     * create test.SpecialGame object and add into the frame.
     * The frame cannot be maximize.
     * Window focus listener is added for the frame.
     * @throws InterruptedException Any interruption during the thread is performing task is ignored
     */

    public void enableSpecialGame() throws InterruptedException {
        this.dispose();
        this.remove(selectionGame);
        specialGame = new SpecialGame(this);
        this.add(specialGame, BorderLayout.CENTER);
        this.setUndecorated(false);
        this.setResizable(false);
        isNormalGame = false;
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * This method removes test.HomeMenu component if the
     * parameter value is true.
     * Selection object is created and will be added
     * into the frame before execute initialize method.
     * @param isFromHomeMenu To identify which frame the user came from
     */
    public void enableSelectionGame(boolean isFromHomeMenu) {
        if (isFromHomeMenu)
            this.remove(homeMenu);
        selectionGame = new SelectionGame(this);
        this.add(selectionGame, BorderLayout.CENTER);
        initialize();
    }

    /**
     * This method removes test.SelectionGame's component
     * and creates test.GameInfo object.
     */

    public void enableInfo() {
        this.remove(selectionGame);
        new GameInfo(this);
    }

    /**
     * This method is to create test.HighScore object.
     * @throws IOException File name that doesn't exists will be ignored
     */

    public void enableScore() throws IOException {
        new HighScore(this);
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
     * This method is to change the state of gaming
     * and this method is used when pop up score appear
     * as a new window that may cause window focus lost.
     */

    public void game() {
        gaming = !gaming;
    }

    /**
     * The first time the frame loses focus is because
     * it has been disposed to install the GameBoard,
     * so went it regains the focus it's ready to play.
     * of course calling a method such as 'onLostFocus'
     * is useful only if the GameBoard as been displayed
     * at least once.
     *
     * @param windowEvent Any window activity performed by the user
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        gaming = true;
    }

    /**
     * This method is executed when the user focus on
     * another window instead of this window.
     * When user is gaming, if the boolean variable is
     * true, it executes the Normal Game, onLostFocus method
     * else it executes another.
     *
     * @param windowEvent Any window activity performed by the user
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (gaming) {
            if(isNormalGame)
                normalGame.onLostFocus();
            else
                specialGame.onLostFocus();
        }
    }
}
