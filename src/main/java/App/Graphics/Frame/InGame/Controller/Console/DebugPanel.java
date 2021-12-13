package App.Graphics.Frame.InGame.Controller.Console;

import App.Graphics.Frame.InGame.Model.GameWall.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class inherits the JPanel class.
 * This class can take control of the level
 * by using buttons and slider and
 * able to reset the ball whenever the
 * user intends to do so as well as the
 * ball speed.
 */

public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private Wall wall;

    /**
     * This constructor assigned buttons and
     * slides and able to perform respective
     * functions whenever the user clicks on
     * individual button or slide on individual
     * slider.
     *
     * @param wall The Wall class
     */

    public DebugPanel(Wall wall) {

        this.wall = wall;

        initialize();

        skipLevel = makeButton("Skip Level", e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls", e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4, 4, e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4, 4, e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);
    }

    /**
     * This method is to set the
     * background with specific colour
     * and set the layout with specific grid.
     */

    private void initialize() {
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2, 2));
    }

    /**
     * Add action listener to the button and
     * set the String inside the button.
     *
     * @param title The String that will be displayed on button
     * @param e The action performed by the user
     * @return The JButton along with its properties
     */

    private JButton makeButton(String title, ActionListener e) {
        JButton out = new JButton(title);
        out.addActionListener(e);
        return out;
    }

    /**
     * This method design the slide and add
     * the change listener into it.
     *
     * @param min The minimum number that can be slide (start point)
     * @param max The maximum number that can be slide (end point)
     * @param e The sliding action by the user
     * @return The JSlider along with its properties
     */

    private JSlider makeSlider(int min, int max, ChangeListener e) {
        JSlider out = new JSlider(min, max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * This method is to set the ball speed
     * in terms of x and y coordinates.
     *
     * @param x The x coordinates speed of ball
     * @param y The y coordinates speed of ball
     */

    public void setValues(int x, int y) {
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }
}
