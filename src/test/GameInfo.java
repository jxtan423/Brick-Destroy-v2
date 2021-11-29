package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class GameInfo extends JComponent implements MouseListener, MouseMotionListener {

    private static final String BACK_TEXT = "Back";

    private static final Color BUTTON_COLOR = new Color(102, 102, 102);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = new Color(255, 255, 0);
    private static final Color CLICKED_TEXT = Color.black;

    private final Rectangle backButton;
    private final Rectangle container;

    private final Font backButtonFont;
    private final Font containerFont;

    private GameFrame owner;
    private DisplayImage image;

    private Dimension area;

    private boolean pointToBack;

    public GameInfo(GameFrame owner) {

        image = new DisplayImage();

        this.owner = owner;
        this.area = image.getArea();

        this.setPreferredSize(area);

        int CONTAINER_WIDTH = 437;
        int CONTAINER_HEIGHT = 100;

        int BTN_WIDTH = getButtonWidth(area);
        int BTN_HEIGHT = getButtonHeight(area);

        Dimension rect = new Dimension(CONTAINER_WIDTH, CONTAINER_HEIGHT);
        container = new Rectangle(rect);

        Dimension btnDim = new Dimension(BTN_WIDTH,BTN_HEIGHT);
        backButton = new Rectangle(btnDim);

        backButtonFont = new Font("Monospaced", Font.PLAIN, backButton.height - 2);
        containerFont = new Font("Monospaced",Font.BOLD,11);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        g.drawImage(image.img(),0,0,null);
        drawMenu((Graphics2D)g);
    }

    private void drawMenu(Graphics2D g2d) {
        Button(g2d);
    }

    private void Button(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D bTxtRect = backButtonFont.getStringBounds(BACK_TEXT, frc);

        g2d.setFont(backButtonFont);

        int x = setBtn_X();
        int y = 400;

        backButton.setLocation(x,y);

        int textWidth = (int) ((backButton.getWidth() - bTxtRect.getWidth()) / 2) + backButton.x ;
        int textHeight = (int) (backButton.getHeight() - bTxtRect.getHeight()) / 2 + (int) (backButton.y + backButton.height * 0.9);

        DrawButton(g2d,textWidth,textHeight,backButton,BACK_TEXT);
    }

    private void DrawButton(Graphics2D g2d, int x, int y, Rectangle button, String text) {
        g2d.setColor(BUTTON_COLOR);
        g2d.fill(button);
        g2d.setColor(TEXT_COLOR);

        if (pointToBack) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.fill(button);
            g2d.setColor(CLICKED_TEXT);
            g2d.draw(button);
            g2d.drawString(text, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(button);
            g2d.drawString(text, x, y);
        }
    }

    private int setBtn_X() {
        return (int)(area.getWidth() / 2 - (backButton.getWidth() / 2));
    }

    private int getButtonHeight(Dimension area) {
        return area.height / 12;
    }

    private int getButtonWidth(Dimension area) {
        return area.width / 3;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (backButton.contains(p)) {
            owner.enableSelectionGame(false);
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
        if (backButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pointToBack = true;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            pointToBack = false;
            repaint();
        }
    }
}
