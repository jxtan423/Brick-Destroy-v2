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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/**
 * This class is to create and design the home page (content)
 * of the frame when user runs the program.
 */

public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 2.0";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";

    private static final Color BUTTON_COLOR = new Color(102, 102, 102);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = new Color(255, 255, 0);
    private static final Color CLICKED_TEXT = Color.black;

    private final Rectangle startButton;
    private final Rectangle menuButton;

    private final Font greetingsFont;
    private final Font gameTitleFont;
    private final Font creditsFont;
    private final Font buttonFont;

    private GameFrame owner;
    private Dimension area;
    private DisplayImage image;

    private boolean pointToStart;
    private boolean pointToExit;


    /**
     * This constructor is to create and design buttons,
     * page border, and specify the fonts.
     *
     * @param owner The details and functionality of the frame
     */

    public HomeMenu(GameFrame owner) {
        image = new DisplayImage(false);

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;
        this.area = image.getArea();

        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);

        greetingsFont = new Font("Noto Mono", Font.PLAIN, 25);
        gameTitleFont = new Font("Noto Mono", Font.BOLD, 40);
        creditsFont = new Font("Monospaced", Font.PLAIN, 10);
        buttonFont = new Font("Monospaced", Font.PLAIN, startButton.height - 2);

    }

    /**
     * The graphics is passed to drawMenu method for painting.
     *
     * @param g The graphics context in which to paint
     */

    public void paint(Graphics g) {
        g.drawImage(image.img(), 0, 0, null);
        drawMenu((Graphics2D) g);
    }

    /**
     * Container, text and button will be painted
     * and design accordingly through method calls.
     *
     * @param g2d The graphics context in which to paint in 2D form
     */

    public void drawMenu(Graphics2D g2d) {
        Text(g2d);
        Button(g2d);
    }

    /**
     * Texts are drawn and designed at respective coordinates.
     *
     * @param g2d The graphics context in which to paint in 2D form
     */

    private void Text(Graphics2D g2d) {

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS, frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE, frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS, frc);

        int gX = identify_X(greetingsRect);
        int tX = identify_X(gameTitleRect);
        int sX = identify_X(creditsRect);

        int gY = identify_Y();
        int tY = identify_Y(gY, gameTitleRect);
        int sY = identify_Y(tY, creditsRect);

        drawText(g2d, greetingsFont, GREETINGS, gX, gY);
        drawText(g2d, gameTitleFont, GAME_TITLE, tX, tY);
        drawText(g2d, creditsFont, CREDITS, sX, sY);
    }

    private void drawText(Graphics2D g2d, Font font, String text, int x, int y) {
        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }

    private int identify_Y(int y, Rectangle2D rect) {
        return y + (int) (rect.getHeight() * 1.1); //add 10% of String height between the two strings
    }

    private int identify_Y() {
        return (int) (area.getHeight() / 2);
    }

    private int identify_X(Rectangle2D rect) {
        return (int) (area.getWidth() - rect.getWidth()) / 2;
    }

    /**
     * The "start" and "exit" buttons are created
     * and designed at respective coordinates.
     * Colour changes whenever the buttons are clicked.
     *
     * @param g2d The graphics context in which to paint in 2D form
     */

    private void Button(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT, frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT, frc);

        g2d.setFont(buttonFont);

        int startX = 64;
        int endX = 277;
        int y = identifyButton_Y();

        startButton.setLocation(startX, y);
        menuButton.setLocation(endX, y);

        int startWidth = buttonWidth(txtRect, startButton);
        int startHeight = buttonHeight(txtRect, startButton);

        int menuWidth = buttonWidth(mTxtRect, menuButton);
        int menuHeight = buttonHeight(mTxtRect, menuButton);

        drawButton(g2d, startWidth, startHeight, startButton, START_TEXT);
        drawButton(g2d, menuWidth, menuHeight, menuButton, MENU_TEXT);
    }

    private int identifyButton_Y() {
        return (int) ((area.height - startButton.height) * 0.8);
    }

    private int buttonHeight(Rectangle2D txtRect, Rectangle button) {
        return (int) ((startButton.getHeight() - txtRect.getHeight()) / 2) + (int) (button.y + (button.height * 0.9));
    }

    private int buttonWidth(Rectangle2D txtRect, Rectangle button) {
        return (int) ((startButton.getWidth() - txtRect.getWidth()) / 2) + button.x;
    }

    private void drawButton(Graphics2D g2d, int x, int y, Rectangle button, String text) {
        g2d.setColor(BUTTON_COLOR);
        g2d.fill(button);
        g2d.setColor(TEXT_COLOR);

        if (pointToStart || pointToExit) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.fill(button);
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(button);
            g2d.drawString(text, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(startButton);
            g2d.drawString(text, x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            owner.enableSelectionGame(true);
        } else if (menuButton.contains(p)) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (pointToStart) {
            pointToStart = false;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);
        } else if (pointToExit) {
            pointToExit = false;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToStart = true;
            pointToExit = false;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);
        } else if (menuButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToStart = false;
            pointToExit = true;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            pointToStart = false;
            pointToExit = false;
            repaint();
        }
    }
}