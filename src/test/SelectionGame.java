package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;

public class SelectionGame extends JComponent implements MouseListener, MouseMotionListener {

    private static final String NORMAL_TEXT = "Normal";
    private static final String SPECIAL_TEXT = "Special";
    private static final String INFO_TEXT = "Info";
    private static final String SCORE_TEXT = "Score";

    private static final Color BUTTON_COLOR = new Color(102, 102, 102);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = new Color(255, 255, 0);
    private static final Color CLICKED_TEXT = Color.black;

    private final Rectangle normalButton;
    private final Rectangle specialButton;
    private final Rectangle infoButton;
    private final Rectangle scoreButton;

    private final Font buttonFont;

    private final GameFrame owner;
    private final DisplayImage image;

    private final Dimension area;
    private final Button btn;

    private boolean pointToNormal;
    private boolean pointToSpecial;
    private boolean pointToInfo;
    private boolean pointToScore;

    public SelectionGame(GameFrame owner) {

        final int AMOUNT_OF_BUTTON = 4;
        this.owner = owner;

        image = new DisplayImage();
        this.area = image.getArea();
        this.setPreferredSize(area);

        btn = new Button(area, AMOUNT_OF_BUTTON);

        Rectangle[] rect = btn.getRect();
        normalButton = rect[0];
        specialButton = rect[1];
        infoButton = rect[2];
        scoreButton = rect[3];

        buttonFont = btn.getFont();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        g.drawImage(image.img(), 0, 0, null);
        drawMenu((Graphics2D) g);
    }

    public void drawMenu(Graphics2D g2d) {
        Button();
        ButtonText(g2d);
    }

    private void Button() {

        Point START_BUTTON = new Point(btn.getButton_X(true), btn.getButton_Y(true));
        Point SPECIAL_BUTTON = new Point(btn.getButton_X(false), btn.getButton_Y(true));
        Point INFO_BUTTON = new Point(btn.getButton_X(true), btn.getButton_Y(false));
        Point SCORE_BUTTON = new Point(btn.getButton_X(false), btn.getButton_Y(false));

        normalButton.setLocation(START_BUTTON);
        specialButton.setLocation(SPECIAL_BUTTON);
        infoButton.setLocation(INFO_BUTTON);
        scoreButton.setLocation(SCORE_BUTTON);
    }

    private void ButtonText(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D nTxtRect = buttonFont.getStringBounds(NORMAL_TEXT, frc);
        Rectangle2D sTxtRect = buttonFont.getStringBounds(SPECIAL_TEXT, frc);
        Rectangle2D iTxtRect = buttonFont.getStringBounds(INFO_TEXT, frc);
        Rectangle2D slTxtRect = buttonFont.getStringBounds(SCORE_TEXT, frc);

        g2d.setFont(buttonFont);

        Text normalTxt = new Text(normalButton, nTxtRect, area, true);
        Text specialTxt = new Text(specialButton, sTxtRect, area, true);
        Text infoTxt = new Text(infoButton, iTxtRect, area, true);
        Text scoreTxt = new Text(scoreButton, slTxtRect, area, true);

        Point NORMAL_TEXT_COORDINATES = new Point(normalTxt.getButton_Text_X(), normalTxt.getButton_Text_Y());
        Point SPECIAL_TEXT_COORDINATES = new Point(specialTxt.getButton_Text_X(), specialTxt.getButton_Text_Y());
        Point INFO_TEXT_COORDINATES = new Point(infoTxt.getButton_Text_X(), infoTxt.getButton_Text_Y());
        Point SCORE_TEXT_COORDINATES = new Point(scoreTxt.getButton_Text_X(), scoreTxt.getButton_Text_Y());

        drawButton(g2d, NORMAL_TEXT_COORDINATES, normalButton, NORMAL_TEXT, pointToNormal);
        drawButton(g2d, SPECIAL_TEXT_COORDINATES, specialButton, SPECIAL_TEXT, pointToSpecial);
        drawButton(g2d, INFO_TEXT_COORDINATES, infoButton, INFO_TEXT, pointToInfo);
        drawButton(g2d, SCORE_TEXT_COORDINATES, scoreButton, SCORE_TEXT, pointToScore);
    }

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
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (normalButton.contains(p)) {
            owner.enableGameBoard();
        } else if (infoButton.contains(p)) {
            owner.enableInfo();
        } else if (specialButton.contains(p)) {
            System.exit(0);
            //owner.enableSpecial();
        } else if (scoreButton.contains(p)) {
            owner.remove(this);
            try {
                owner.enableScore();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if (normalButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToNormal = true;
            repaint(normalButton.x, normalButton.y, normalButton.width + 1, normalButton.height + 1);
        } else if (specialButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToSpecial = true;
            repaint(specialButton.x, specialButton.y, specialButton.width + 1, specialButton.height + 1);
        } else if (infoButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToInfo = true;
            repaint(infoButton.x, infoButton.y, infoButton.width + 1, infoButton.height + 1);
        } else if (scoreButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToScore = true;
            repaint(scoreButton.x, scoreButton.y, scoreButton.width + 1, scoreButton.height + 1);
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            pointToNormal = false;
            pointToSpecial = false;
            pointToInfo = false;
            pointToScore = false;
            repaint();
        }
    }
}
