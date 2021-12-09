package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SelectionGame extends Image implements MouseListener, MouseMotionListener {

    private static final String NORMAL_TEXT = "Normal";
    private static final String SPECIAL_TEXT = "Special";
    private static final String INFO_TEXT = "Info";
    private static final String SCORE_TEXT = "Score";

    private Rectangle normalButton;
    private Rectangle specialButton;
    private Rectangle infoButton;
    private Rectangle scoreButton;

    private Font buttonFont;

    private final GameFrame owner;

    private final Dimension area;
    private Button btn;

    private boolean pointToNormal;
    private boolean pointToSpecial;
    private boolean pointToInfo;
    private boolean pointToScore;

    public SelectionGame(GameFrame owner) {

        super();
        this.owner = owner;
        this.area = super.getArea();
        button();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void button() {
        int AMOUNT_OF_BUTTON = 4;
        btn = new Button(area, AMOUNT_OF_BUTTON, true);
        Rectangle[] rect = btn.getRect();
        infoButton = rect[0];
        scoreButton = rect[1];
        normalButton = rect[2];
        specialButton = rect[3];

        buttonFont = btn.getFont();
    }

    @Override
    public void content() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawMenu((Graphics2D) g);
    }

    public void drawMenu(Graphics2D g2d) {
        Button(g2d);
    }

    private void Button(Graphics2D g2d) {

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

        Point NORMAL_TEXT_COORDINATES = new Point(normalTxt.getCoordinate().x, normalTxt.getCoordinate().y);
        Point SPECIAL_TEXT_COORDINATES = new Point(specialTxt.getCoordinate().x, specialTxt.getCoordinate().y);
        Point INFO_TEXT_COORDINATES = new Point(infoTxt.getCoordinate().x, infoTxt.getCoordinate().y);
        Point SCORE_TEXT_COORDINATES = new Point(scoreTxt.getCoordinate().x, scoreTxt.getCoordinate().y);

        drawButton(g2d, NORMAL_TEXT_COORDINATES, normalButton, NORMAL_TEXT, pointToNormal);
        drawButton(g2d, SPECIAL_TEXT_COORDINATES, specialButton, SPECIAL_TEXT, pointToSpecial);
        drawButton(g2d, INFO_TEXT_COORDINATES, infoButton, INFO_TEXT, pointToInfo);
        drawButton(g2d, SCORE_TEXT_COORDINATES, scoreButton, SCORE_TEXT, pointToScore);
    }

    private void drawButton(Graphics2D g2d, Point coordinates, Rectangle button, String text, boolean pointToButton) {
        g2d.setColor(Color.GRAY);
        g2d.fill(button);
        g2d.setColor(Color.WHITE);

        if (pointToButton) {
            g2d.setColor(Color.YELLOW);
            g2d.fill(button);
            g2d.setColor(Color.BLACK);
        }
        g2d.draw(button);
        g2d.drawString(text, (int) coordinates.getX(), (int) coordinates.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (normalButton.contains(p)) {
            owner.enableNormalGame();
        } else if (infoButton.contains(p)) {
            owner.enableInfo();
        } else if (specialButton.contains(p)) {
            try {
                owner.enableSpecialGame();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        } else if (scoreButton.contains(p)) {
            owner.remove(this);
            try {
                owner.enableScore();
            } catch (IOException ex) {
                ex.printStackTrace();
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
