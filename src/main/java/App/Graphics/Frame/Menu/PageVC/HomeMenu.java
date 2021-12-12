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

/**
 * This class display the Home Menu page
 * along with images, buttons and texts
 */

public class HomeMenu extends Image implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 2.0";
    private static final String START_TEXT = "Start";
    private static final String EXIT_TEXT = "Exit";

    private static final Color BUTTON_COLOR = new Color(102, 102, 102);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = new Color(255, 255, 0);
    private static final Color CLICKED_TEXT = Color.black;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle empty;

    private final GameFrame owner;
    private final Dimension area;
    private Button button;

    private boolean pointToStart;
    private boolean pointToExit;

    /**
     * This constructor includes the buttons
     * and content inside the frame.
     * Buttons are clickable since MouseListener
     * and MouseMotionListener
     * are implemented and added.
     *
     * @param owner Control GameFrame method whenever event is captured
     */

    public HomeMenu(GameFrame owner) {
        super();
        this.owner = owner;
        this.area = super.getArea();
        button();
        content();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * This method creates buttons by creating
     * button object in array form.
     * An empty button is created for non-button text.
     */

    @Override
    public void button() {
        int AMOUNT_OF_BUTTON = 2;
        button = new Button(area, AMOUNT_OF_BUTTON, true);
        Rectangle[] rect = button.getRect();
        startButton = rect[0];
        exitButton = rect[1];
        empty = new Rectangle(0, 0);
    }

    /**
     * Initialize both the font for non-button text
     * and button text
     */

    @Override
    public void content() {

        int GREETINGS_FONT_SIZE = 25;
        int GAME_TITLE_FONT_SIZE = 40;
        int CREDITS_FONT_SIZE = 10;

        greetingsFont = new Font("Noto Mono", Font.PLAIN, GREETINGS_FONT_SIZE);
        gameTitleFont = new Font("Noto Mono", Font.BOLD, GAME_TITLE_FONT_SIZE);
        creditsFont = new Font("Monospaced", Font.PLAIN, CREDITS_FONT_SIZE);
        buttonFont = button.getFont();
    }

    /**
     * Image is drawn through parent class.
     * The graphics is passed to drawHomeMenu method
     * for further drawing and painting.
     *
     * @param g The graphics context on which to paint
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawHomeMenu((Graphics2D) g);
    }

    /**
     * This method calls FontRenderContext for getting the
     * string width and height in further.
     * Execute the text and button methods.
     *
     * @param g2d The graphics context on which to paint in 2D form
     */

    public void drawHomeMenu(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();
        Text(g2d, frc);
        Button(g2d, frc);
    }

    /**
     * Colour is set for non-button texts.
     * 3 text objects are created with 3 different words.
     * Title and credits texts are further coordinated under
     * setter method in Text class.
     * The coordinates are set for three non-button texts and pass to
     * drawText method to draw and paint
     *
     * @param g2d The graphics context on which to paint in 2D form
     */

    public void Text(Graphics2D g2d, FontRenderContext frc) {

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS, frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE, frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS, frc);

        g2d.setColor(TEXT_COLOR);

        Text greeting = new Text(empty, greetingsRect, area, false);
        Text title = new Text(empty, gameTitleRect, area, false);
        Text credits = new Text(empty, creditsRect, area, false);

        title.setNewText_Y(greeting.getCoordinate().y);
        credits.setNewText_Y(title.getCoordinate().y);

        Point GREETING_COORDINATES = new Point(greeting.getCoordinate().x, greeting.getCoordinate().y);
        Point TITLE_COORDINATES = new Point(title.getCoordinate().x, title.getCoordinate().y);
        Point CREDITS_COORDINATES = new Point(credits.getCoordinate().x, credits.getCoordinate().y);

        drawText(g2d, greetingsFont, GREETINGS, GREETING_COORDINATES);
        drawText(g2d, gameTitleFont, GAME_TITLE, TITLE_COORDINATES);
        drawText(g2d, creditsFont, CREDITS, CREDITS_COORDINATES);
    }

    /**
     * The texts are drawn with specific font.
     *
     * @param g2d         The graphics context on which to paint in 2D form
     * @param font        The font of the text
     * @param text        The content that will be drawn in the form of string
     * @param coordinates The coordinates of text
     */

    public void drawText(Graphics2D g2d, Font font, String text, Point coordinates) {
        g2d.setFont(font);
        g2d.drawString(text, (int) coordinates.getX(), (int) coordinates.getY());
    }

    /**
     * The coordinates of "start" and "exit"
     * buttons are set.
     * The coordinates of the texts inside the container
     * are set.
     * Both buttons and texts are drawn through
     * drawButton method.
     *
     * @param g2d The graphics context on which to paint in 2D form
     * @param frc The information needed to correctly measure text inside a container
     */

    private void Button(Graphics2D g2d, FontRenderContext frc) {

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT, frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(EXIT_TEXT, frc);

        g2d.setFont(buttonFont);

        Text startTxt = new Text(startButton, txtRect, area, true);
        Text exitTxt = new Text(exitButton, mTxtRect, area, true);

        Point START_TEXT_COORDINATES = new Point(startTxt.getCoordinate().x, startTxt.getCoordinate().y);
        Point EXIT_TEXT_COORDINATES = new Point(exitTxt.getCoordinate().x, exitTxt.getCoordinate().y);

        drawButton(g2d, START_TEXT_COORDINATES, startButton, START_TEXT, pointToStart);
        drawButton(g2d, EXIT_TEXT_COORDINATES, exitButton, EXIT_TEXT, pointToExit);
    }

    /**
     * Button is drawn and filled with specific font
     * and colour.
     * test.
     * Text inside the container is drawn
     * with specific font.
     * The button change colour whenever the mouse points
     * to the respective button.
     *
     * @param g2d           The graphics context on which to paint in 2D form
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

    /**
     * This method gets the coordinate of the mouse.
     * Whenever the mouse clicks on either of the buttons,
     * the respective buttons will perform the respective
     * function.
     *
     * @param mouseEvent Mouse activity made by user
     */

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

    /**
     * This method does nothing.
     *
     * @param mouseEvent Mouse activity made bu user
     */

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    /**
     * Whenever the mouse points to any button,
     * it repaints the buttons and set the boolean
     * variable to true for paint method to recognise.
     *
     * @param mouseEvent Mouse activity made by user
     */
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

    /**
     * This method does nothing.
     *
     * @param mouseEvent Mouse activity made bu user
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    /**
     * This method does nothing.
     *
     * @param mouseEvent Mouse activity made bu user
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    /**
     * This method does nothing.
     *
     * @param mouseEvent Mouse activity made bu user
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
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
     * @param mouseEvent Mouse activity made bu user
     */
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