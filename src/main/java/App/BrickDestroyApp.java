package App;

import App.Graphics.Frame.GameFrame;
import java.awt.*;

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
 *  along with this program. If not, see http://www.gnu.org/licenses/
 *
 * @author Tan Jian Xin
 * @version 2.0
 */

public class BrickDestroyApp {

    /**
     * This main method will invoke GameFrame constructor and initialize method
     * in GameFrame class.
     *
     * @param args The command line arguments
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameFrame().initialize());
    }

}
