package App.Graphics.Frame.InGame;

import App.Graphics.Frame.GameFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class inherits score class to get
 * the parents' attributes.
 * It displays the score after the user
 * completes every single level in normal
 * game as well as special game.
 */

public class PopUpScore extends Score {

    private final JFrame frame;
    private JLabel marks;

    private final GameFrame owner;
    private double score;
    private double highScore;

    /**
     * This constructor calls its parents constructor
     * to get its attributes.
     * Frame and button are assigned by getter from the parent class.
     * It executes the content method.
     *
     * @param owner Control GameFrame method whenever event is captured
     */

    public PopUpScore(GameFrame owner) {
        super("Show Score", "Continue");
        frame = super.getFrame();
        this.owner = owner;
        content();
        new PopUpScoreController(this);
    }

    /**
     * This method is to calculate the time taken for
     * the user complete each level.
     * Several methods will be executed and the final
     * score which is the high score will be set through
     * the set method.
     *
     * @param min The minutes used to complete each level
     * @param sec The seconds used to complete each level
     */

    public void setScore(int min, int sec) {
        int seconds = allInSeconds(min, sec);
        score = calculateScore(seconds);
        score = checkScore(score);
        setHighScore(score);
    }

    /**
     * @return The score of the user gained in a single level
     */

    public double getScore() {
        return score;
    }

    /**
     * Set the score.
     * @param score The score the user gained in a single level
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Convert the minutes into seconds and add it to
     * get total seconds.
     *
     * @param minutes The minutes used to complete each level
     * @param seconds The seconds used to complete each level
     * @return The total seconds
     */

    private int allInSeconds(int minutes, int seconds) {
        return seconds + (minutes * 60);
    }

    /**
     * This method calculates the score for the user.
     * Each round contains 100 points and the points
     * will be deducted based on how long the user
     * completes the level.
     *
     * @param seconds The seconds used to complete each level
     * @return The score that user gained
     */

    private double calculateScore(int seconds) {
        return 100.0 - (seconds * 0.1);
    }

    /**
     * This method is to identify whether the score
     * is lower than 0.
     * There's no point to give a negative score.
     * So whenever the score is less than 0, the score
     * will automatically set as 0.
     *
     * @param score The score that the user gained
     * @return The score that the user deserve
     */

    private double checkScore(double score) {
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    /**
     * @return The total score that the user gained
     */

    public double getHighScore() {
        return highScore;
    }

    /**
     * Set the high score by adding the current high
     * score with the current score.
     *
     * @param score The score that the user gained
     */

    public void setHighScore(double score) {
        highScore += score;
    }

    /**
     * This method is to show the score that
     * the user gained on user's screen.
     */

    public void scoreVisible() {
        marks.setText(String.valueOf(score));
        frame.setVisible(true);
    }

    /**
     * This method is to add a label to identify
     * this frame is for displaying the score for
     * the user as well as the
     * score scored by the user.
     */

    @Override
    public void content() {
        Rectangle LABEL = new Rectangle(10, 0, 250, 30);
        Rectangle MARKS = new Rectangle(75, 30, 100, 30);

        JLabel label = new JLabel("Your score is : ");
        label.setBounds(LABEL);
        label.setFont(new Font(null, Font.BOLD, 15));

        marks = new JLabel();
        marks.setBounds(MARKS);
        marks.setFont(new Font(null, Font.ITALIC, 30));

        frame.add(marks);
        frame.add(label);
    }

    /**
     * This method is to get the
     * GameFrame.
     *
     * @return The GameFrame where the components are created
     */

    public GameFrame getOwner() {
        return this.owner;
    }
}
