package test;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class PauseMenu extends JComponent {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";

    private final Dimension area;
    private Rectangle continueBtn;
    private Rectangle restartBtn;
    private Rectangle exitBtn;
    private Button btn;

    public PauseMenu(Dimension area) {
        this.area = area;
        setBtn();
    }

    private void setBtn() {
        btn = new Button(area, 3, false);
        Rectangle[] rect = btn.getRect();
        continueBtn = rect[0];
        restartBtn = rect[1];
        exitBtn = rect[2];
    }

    public void paint(Graphics g) {
        obscureGameBoard((Graphics2D) g);
        drawPauseMenu((Graphics2D) g);
    }

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

    private void drawPauseMenu(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        g2d.setFont(btn.getFont());

        Rectangle2D txtRect = btn.getFont().getStringBounds(CONTINUE, frc);
        Rectangle2D rTxtRect = btn.getFont().getStringBounds(RESTART, frc);
        Rectangle2D eTxtRect = btn.getFont().getStringBounds(EXIT, frc);

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

    public Rectangle getContinueBtn() {
        return this.continueBtn;
    }

    public Rectangle getRestartBtn() {
        return this.restartBtn;
    }

    public Rectangle getExitBtn() {
        return this.exitBtn;
    }
}
