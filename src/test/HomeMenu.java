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
    private static final String EXIT_TEXT = "Exit";

    private static final Color BUTTON_COLOR = new Color(102, 102, 102);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = new Color(255, 255, 0);
    private static final Color CLICKED_TEXT = Color.black;

    private final Font greetingsFont;
    private final Font gameTitleFont;
    private final Font creditsFont;
    private final Font buttonFont;

    private final Rectangle startButton;
    private final Rectangle exitButton;

    private final GameFrame owner;
    private final Dimension area;
    private final DisplayImage image;
    private final Button btn;

    private boolean pointToStart;
    private boolean pointToExit;

    /**
     * This constructor is to create and design buttons,
     * texts with specific fonts and colours.
     * Image object is invoked and the size of image is
     * initialized to the current frame area.
     * Buttons are clickable since MouseListener
     * is implemented and added.
     *
     * @param owner The details and functionality of the frame
     */

    public HomeMenu(GameFrame owner) {

        final int AMOUNT_OF_BUTTON = 2;
        final int GREETINGS_FONT_SIZE = 25;
        final int GAME_TITLE_FONT_SIZE = 40;
        final int CREDITS_FONT_SIZE = 10;

        this.owner = owner;

        image = new DisplayImage();
        this.area = image.getArea();
        this.setPreferredSize(area);

        btn = new Button(area, AMOUNT_OF_BUTTON);

        Rectangle[] rect = btn.getRect();
        startButton = rect[0];
        exitButton = rect[1];

        greetingsFont = new Font("Noto Mono", Font.PLAIN, GREETINGS_FONT_SIZE);
        gameTitleFont = new Font("Noto Mono", Font.BOLD, GAME_TITLE_FONT_SIZE);
        creditsFont = new Font("Monospaced", Font.PLAIN, CREDITS_FONT_SIZE);
        buttonFont = btn.getFont();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Image is drawn by getting from image class.
     * The graphics is passed to drawHomeMenu method
     * for further drawing and painting.
     *
     * @param g The graphics context in which to paint
     */

    public void paint(Graphics g) {
        g.drawImage(image.img(), 0, 0, null);
        drawHomeMenu((Graphics2D) g);
    }

    /**
     * In HomeMenu frame, there are texts and
     * buttons to be drawn and painted.
     *
     * @param g2d The graphics context in which to paint in 2D form
     */

    public void drawHomeMenu(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();
        Text(g2d, frc);
        Button();
        ButtonText(g2d, frc);
    }

    /**
     * Texts are coordinated.
     * Texts are drawn through drawTest method.
     *
     * @param g2d The graphics context in which to paint in 2D form
     */

    private void Text(Graphics2D g2d, FontRenderContext frc) {

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS, frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE, frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS, frc);

        g2d.setColor(TEXT_COLOR);

        Text greeting = new Text(null, greetingsRect, area, false);
        Text title = new Text(null, gameTitleRect, area, false);
        Text credits = new Text(null, creditsRect, area, false);

        title.setNewText_Y(greeting.getText_Y());
        credits.setNewText_Y(title.getText_Y());

        Point GREETING_COORDINATES = new Point(greeting.getText_X(), greeting.getText_Y());
        Point TITLE_COORDINATES = new Point(title.getText_X(), title.getText_Y());
        Point CREDITS_COORDINATES = new Point(credits.getText_X(), credits.getText_Y());

        drawText(g2d, greetingsFont, GREETINGS, GREETING_COORDINATES);
        drawText(g2d, gameTitleFont, GAME_TITLE, TITLE_COORDINATES);
        drawText(g2d, creditsFont, CREDITS, CREDITS_COORDINATES);

    }

    /**
     * The texts are drawn with specific font.
     *
     * @param g2d The graphics context in which to paint in 2D form
     * @param font The font of the text
     * @param text The content that will be drawn in the form of string
     * @param coordinates The coordinates of text
     */

    private void drawText(Graphics2D g2d, Font font, String text, Point coordinates) {
        g2d.setFont(font);
        g2d.drawString(text, (int) coordinates.getX(), (int) coordinates.getY());
    }

    /**
     * The coordinates of "start" and "exit"
     * buttons are set.
     */

    private void Button() {

        Point START_BUTTON = new Point(btn.getButton_X(true), btn.getButton_Y(false));
        Point EXIT_BUTTON = new Point(btn.getButton_X(false), btn.getButton_Y(false));

        startButton.setLocation(START_BUTTON);
        exitButton.setLocation(EXIT_BUTTON);
    }

    /**
     * The coordinates of the texts inside the container
     * are set.
     * Both buttons and texts are drawn through
     * drawButton method.
     *
     * @param g2d The graphics context in which to paint in 2D form
     * @param frc The information needed to correctly measure text inside a container
     */

    private void ButtonText(Graphics2D g2d, FontRenderContext frc) {

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT, frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(EXIT_TEXT, frc);

        g2d.setFont(buttonFont);

        Text startTxt = new Text(startButton, txtRect, area,true);
        Text exitTxt = new Text(exitButton, mTxtRect, area,true);

        Point START_TEXT_COORDINATES = new Point(startTxt.getButton_Text_X(), startTxt.getButton_Text_Y());
        Point EXIT_TEXT_COORDINATES = new Point(exitTxt.getButton_Text_X(), exitTxt.getButton_Text_Y());

        drawButton(g2d, START_TEXT_COORDINATES, startButton, START_TEXT, pointToStart);
        drawButton(g2d, EXIT_TEXT_COORDINATES, exitButton, EXIT_TEXT, pointToExit);
    }

    /**
     * Button is drawn and filled with specific font
     * and colour.
     * Text inside the container is drawn
     * with specific font.
     * The button change colour whenever the mouse points
     * to the respective button.
     *
     * @param g2d           The graphics context in which to paint in 2D form
     * @param coordinates   The coordinates of texts
     * @param button        The coordinates and area of both buttons
     * @param text          The content that will be drawn in the form of words
     * @param pointToButton Return true when the mouse points the button
     */

    private void drawButton(Graphics2D g2d, Point coordinates, Rectangle button, String text, boolean pointToButton) {
        g2d.setColor(BUTTON_COLOR);
        g2d.fill(button);
        g2d.setColor(TEXT_COLOR);

        if (pointToButton) {
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.fill(button);
            g2d.setColor(CLICKED_TEXT);
        }
        g2d.draw(button);
        g2d.drawString(text, (int) coordinates.getX(), (int) coordinates.getY());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            owner.enableSelectionGame(true);
        } else if (exitButton.contains(p)) {
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
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height + 1);
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
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);
        } else if (exitButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToExit = true;
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height + 1);
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            pointToStart = false;
            pointToExit = false;
            repaint();
        }
    }
}