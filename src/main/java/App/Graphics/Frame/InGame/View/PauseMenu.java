package App.Graphics.Frame.InGame.View;

import App.Graphics.Frame.Menu.Attributes.Button;
import App.Graphics.Frame.Menu.Attributes.Text;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * This class inherits the JComponent.
 * This class will display the Pause Menu
 * by painting include buttons with texts
 * along with specific fonts and colours.
 *
 */

public class PauseMenu extends JComponent {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";

    private final Dimension area;
    private Rectangle continueBtn;
    private Rectangle restartBtn;
    private Rectangle exitBtn;
    private Button button;

    /**
     * This constructor is to assign the
     * area from the parameters.
     * It executes the setBtn method.
     *
     * @param area The area of the normal game frame
     */
    public PauseMenu(Dimension area) {
        this.area = area;
        setBtn();
    }

    /**
     * This method will get the button
     * required in this class by instantiate
     * the button class to create an array of
     * rectangle.
     *
     */

    private void setBtn() {
        button = new Button(area, 3, false);
        Rectangle[] rect = button.getRect();
        continueBtn = rect[0];
        restartBtn = rect[1];
        exitBtn = rect[2];
    }

    /**
     * This method will execute obscureGameBoard
     * and drawPauseMenu methods.
     *
     * @param g The graphics context on which to paint
     */
    public void paint(Graphics g) {
        obscureGameBoard((Graphics2D) g);
        drawPauseMenu((Graphics2D) g);
    }

    /**
     * This method set the background to become
     * black colour with transparency.
     *
     * @param g2d The graphics context on which to paint in 2D form
     */

    private void obscureGameBoard(Graphics2D g2d) {

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, area.width, area.height);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * This method instantiate the Text class
     * and get the width and height of the text
     * as well as the coordinates of the text.
     * The colour of all buttons are filled and the
     * texts are drawn into the buttons.
     * @param g2d The graphics context on which to paint in 2D form
     */

    private void drawPauseMenu(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        g2d.setFont(button.getFont());

        Rectangle2D txtRect = button.getFont().getStringBounds(CONTINUE, frc);
        Rectangle2D rTxtRect = button.getFont().getStringBounds(RESTART, frc);
        Rectangle2D eTxtRect = button.getFont().getStringBounds(EXIT, frc);

        Text txt = new Text(continueBtn, txtRect, area, true);
        Text rTxt = new Text(restartBtn, rTxtRect, area, true);
        Text eTxt = new Text(exitBtn, eTxtRect, area, true);

        g2d.setColor(Color.GRAY);
        g2d.fill(continueBtn);
        g2d.fill(restartBtn);
        g2d.fill(exitBtn);

        g2d.setColor(Color.WHITE);
        g2d.drawString(CONTINUE, txt.getCoordinate().x, txt.getCoordinate().y);
        g2d.drawString(RESTART, rTxt.getCoordinate().x, rTxt.getCoordinate().y);
        g2d.drawString(EXIT, eTxt.getCoordinate().x, eTxt.getCoordinate().y);
    }

    /**
     * This method will get the
     * "Continue" button.
     *
     * @return The "Continue" button
     */

    public Rectangle getContinueBtn() {
        return this.continueBtn;
    }

    /**
     * This method will get the
     * "Restart" button.
     *
     * @return The "Restart" button
     */

    public Rectangle getRestartBtn() {
        return this.restartBtn;
    }

    /**
     * This method will get the
     * "Exit" button.
     *
     * @return The "Exit" button
     */

    public Rectangle getExitBtn() {
        return this.exitBtn;
    }
}
