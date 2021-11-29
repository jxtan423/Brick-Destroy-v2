package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Score implements ActionListener {

    private double score;
    private double highScore;
    private String word;

    private final JFrame frame;
    private JLabel label;
    private final JButton btn;
    private final JTextField field;

    private GameFrame owner;

    private final static int DEF_WIDTH = 250;
    private final static int DEF_HEIGHT = 100;

    public Score(GameFrame owner) {

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        this.owner = owner;

        frame = new JFrame("Submit");
        frame.setSize(DEF_WIDTH, DEF_HEIGHT);
        frame.setUndecorated(true);
        frame.setLocation((size.width / 2) - (frame.getWidth() / 2), size.height / 2);
        frame.setLayout(null);

        btn = new JButton("Submit");
        btn.setBounds(72, 65, 100, 30);
        frame.add(btn);

        label = new JLabel("Please enter your name : ");
        label.setBounds(10, 0, 250, 30);
        label.setFont(new Font(null, Font.PLAIN, 12));
        frame.add(label);

        field = new JTextField();
        field.setBounds(50, 35, 150, 20);
        frame.add(field);

        btn.addActionListener(this);
    }

    private double calculateScore(int seconds) {
        return 100.0 - (seconds * 0.1);
    }

    private int allInSeconds(int minutes, int seconds) {
        return seconds + (minutes * 60);
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

    private double checkScore(double score) {
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    public void setHighScore(double score) {
        highScore += score;
    }

    public double getHighScore() {
        return highScore;
    }

    public void showScore() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            try {
                word = field.getText();
                File file = new File("scoreboard.txt");
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter buffer = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(buffer);
                printWriter.printf("%s %.2f\n", word, getHighScore());
                printWriter.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                owner.enableScore();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            owner.dispose();
            frame.dispose();
        }
    }
}
