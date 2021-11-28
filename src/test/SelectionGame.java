package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class SelectionGame extends JComponent implements MouseListener, MouseMotionListener {

    private static final String NORMAL_TEXT = "Normal";
    private static final String SPECIAL_TEXT = "Special";
    private static final String INFO_TEXT = "Info";
    private static final String SCORE_TEXT = "Score";

    private static final Color BUTTON_COLOR = new Color(102, 102, 102);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = new Color(255, 255, 0);
    private static final Color CLICKED_TEXT = Color.black;

    private static final int normalBtn = 0;
    private static final int specialBtn = 65;
    private static final int infoBtn = 130;
    private static final int scoreBtn = 195;

    private final Rectangle normalButton;
    private final Rectangle specialButton;
    private final Rectangle infoButton;
    private final Rectangle scoreButton;

    private final Font buttonFont;

    private GameFrame owner;
    private DisplayImage image;
    private Score score;

    private Dimension area;

    private boolean pointToNormal;
    private boolean pointToSpecial;
    private boolean pointToInfo;
    private boolean pointToScore;


    public SelectionGame(GameFrame owner) {

        image = new DisplayImage();
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;
        this.area = image.getArea();

        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        normalButton = new Rectangle(btnDim);
        specialButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        scoreButton = new Rectangle(btnDim);

        buttonFont = new Font("Monospaced", Font.PLAIN, normalButton.height - 2);

    }

    public void paint(Graphics g) {
        g.drawImage(image.img(), 0, 0, null);
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

        int x = setBtn_X();
        int nY = setBtn_Y(normalBtn);
        int sY = setBtn_Y(specialBtn);
        int iY = setBtn_Y(infoBtn);
        int slY = setBtn_Y(scoreBtn);

        normalButton.setLocation(x, nY);
        specialButton.setLocation(x, sY);
        infoButton.setLocation(x, iY);
        scoreButton.setLocation(x, slY);

        int normalText_X = textWidth(nTxtRect, normalButton);
        int normalText_Y = textHeight(nTxtRect, normalButton);

        int specialText_X = textWidth(sTxtRect, specialButton);
        int specialText_Y = textHeight(sTxtRect, specialButton);

        int infoText_X = textWidth(iTxtRect, infoButton);
        int infoText_Y = textHeight(iTxtRect, infoButton);

        int scoreText_X = textWidth(slTxtRect, scoreButton);
        int scoreText_Y = textHeight(slTxtRect, scoreButton);

        drawButton(g2d, normalText_X, normalText_Y, normalButton, NORMAL_TEXT, pointToNormal);
        drawButton(g2d, specialText_X, specialText_Y, specialButton, SPECIAL_TEXT, pointToSpecial);
        drawButton(g2d, infoText_X, infoText_Y, infoButton, INFO_TEXT, pointToInfo);
        drawButton(g2d, scoreText_X, scoreText_Y, scoreButton, SCORE_TEXT, pointToScore);
    }

    private int textHeight(Rectangle2D TxtRect, Rectangle button) {
        return (int) (normalButton.getHeight() - TxtRect.getHeight()) / 2 + (int) (button.y + button.height * 0.9);
    }

    private int textWidth(Rectangle2D TxtRect, Rectangle button) {
        return (int) ((normalButton.getWidth() - TxtRect.getWidth()) / 2) + button.x;
    }

    private int setBtn_Y(int addBtnHgt) {
        return (int) (area.getHeight() / 2) + addBtnHgt;
    }

    private int setBtn_X() {
        return (int) (area.getWidth() / 2 - (normalButton.getWidth() / 2));
    }

    private void drawButton(Graphics2D g2d, int x, int y, Rectangle button, String text, boolean pointToButton) {
        g2d.setColor(BUTTON_COLOR);
        g2d.fill(button);
        g2d.setColor(TEXT_COLOR);

        if (pointToButton) {
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.fill(button);
            g2d.setColor(CLICKED_TEXT);
        }
        g2d.draw(button);
        g2d.drawString(text, x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (normalButton.contains(p)) {
            owner.enableGameBoard();
        } else if (infoButton.contains(p)) {
            owner.enableInfo();
        } else if (specialButton.contains(p)) {
            //score = new Score("Go to next level");
            System.exit(0);
            //owner.enableSpecial();
        } else if (scoreButton.contains(p)) {
            try {
                owner.enableScore();
            } catch (IOException ioException) {
                ioException.printStackTrace();
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
