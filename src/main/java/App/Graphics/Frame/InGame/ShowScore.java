package App.Graphics.Frame.InGame;

import App.Graphics.Frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowScore extends Score {

    private final JFrame frame;
    private final JButton btn;
    private JLabel marks;

    private GameFrame owner;
    private double score;
    private double highScore;

    public ShowScore(GameFrame owner) {
        super("Show Score", "Continue");
        frame = super.getFrame();
        btn = super.getBtn();
        this.owner = owner;
        content();
    }

    public double getScore() {
        return score;
    }

    public void setScore(int min, int sec) {
        int seconds = allInSeconds(min, sec);
        score = calculateScore(seconds);
        score = checkScore(score);
        setHighScore(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int allInSeconds(int minutes, int seconds) {
        return seconds + (minutes * 60);
    }

    private double calculateScore(int seconds) {
        return 100.0 - (seconds * 0.1);
    }

    private double checkScore(double score) {
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    public double getHighScore() {
        return highScore;
    }

    public void setHighScore(double score) {
        highScore += score;
    }

    public void scoreVisible() {
        marks.setText(String.valueOf(score));
        frame.setVisible(true);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            frame.dispose();
            owner.game();
        }
    }
}
