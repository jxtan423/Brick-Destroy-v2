package App.Graphics.Frame.Menu.PageVC;

import App.Graphics.Frame.GameFrame;
import App.Graphics.Frame.Menu.Attributes.Button;
import App.Graphics.Frame.Menu.Attributes.Text;
import App.Graphics.Image;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

/**
 * This class display the selection game on user's
 * screen bu displaying 4 buttons, "Normal", "Special",
 * "Info" and "Score" buttons along with image.
 */

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

    private boolean pointToNormal;
    private boolean pointToSpecial;
    private boolean pointToInfo;
    private boolean pointToScore;

    /**
     * This constructor calls its parents to include parents'
     * attributes.
     * It executes button method.
     * This frame will be getting focus and MouseListener and
     * MouseMotionListener will be added as well.
     *
     * @param owner Control GameFrame method whenever event is captured
     */

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

    /**
     * This method creates buttons by creating
     * button object in array form.
     */

    @Override
    public void button() {
        int AMOUNT_OF_BUTTON = 4;
        Button button = new Button(area, AMOUNT_OF_BUTTON, true);
        Rectangle[] rect = button.getRect();
        infoButton = rect[0];
        scoreButton = rect[1];
        normalButton = rect[2];
        specialButton = rect[3];
        buttonFont = button.getFont();
    }

    /**
     * This method does nothing considering
     * this class doesn't displaying any
     * content apart from buttons.
     */

    @Override
    public void content() {
    }

    /**
     * Image is drawn through parent class.
     * This method will execute drawMenu method along
     * with graphics parameter in 2D form.
     *
     * @param g The graphics context in which to paint
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawMenu((Graphics2D) g);
    }

    /**
     * Button method will be executed along with
     * g2d parameter to pass to Button method.
     *
     * @param g2d The graphics context in which to paint in 2D form
     */

    public void drawMenu(Graphics2D g2d) {
        Button(g2d);
    }

    /**
     * This method gets button text width and height.
     * The text font is set.
     * Text objects are created with 4 different wordings.
     * The coordinate for the button texts are coordinated.
     * The drawButton method is executed.
     *
     * @param g2d The graphics context in which to paint in 2D form
     */

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

    /**
     * The colour of the button and button text are set.
     * Whenever the boolean variable is true, the button
     * and button text will change colour.
     * The button and text are drawn and painted.
     *
     * @param g2d           The graphics context in which to paint in 2D form
     * @param coordinates   The coordinates of the button text
     * @param button        The button in the frame
     * @param text          The words that should be displayed
     * @param pointToButton Identify whether the mouse points to the button or not
     */

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

    /**
     * This method gets the coordinate of the mouse.
     * Whenever the mouse clicks on either of the buttons,
     * the respective buttons will perform the respective
     * function.
     *
     * @param e Mouse activity made by user
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (normalButton.contains(p))
            owner.enableNormalGame();
        else if (infoButton.contains(p))
            owner.enableInfo();
        else if (specialButton.contains(p)) {
            try {
                owner.enableSpecialGame();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
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

    /**
     * This method does nothing.
     *
     * @param e Mouse activity made bu user
     */

    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e Mouse activity made bu user
     */

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e Mouse activity made bu user
     */

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e Mouse activity made bu user
     */

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * This method does nothing.
     *
     * @param e Mouse activity made bu user
     */

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * This method gets the coordinate of the mouse.
     * Whenever the mouse points to any buttons,
     * the cursor will turn to hand cursor.
     * The respective boolean variable will be initialize
     * as true for paint method to recognise
     * and execute repaint function after that.
     * Else, the cursor will turn to default mode and
     * the boolean variable will be assigned to false.
     *
     * @param e Mouse activity made bu user
     */

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
