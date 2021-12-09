package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class SubmitScore extends Score {

    private final JFrame frame;
    private final JButton btn;
    private JTextField field;
    private final GameFrame owner;

    private double highScore;

    public SubmitScore(GameFrame owner) {
        super("Submit Score", "Submit");
        this.owner = owner;
        frame = super.getFrame();
        btn = super.getBtn();
        content();
    }

    @Override
    public void content() {
        Rectangle LABEL = new Rectangle(10, 0, 250, 30);
        Rectangle FIELD = new Rectangle(50, 35, 150, 20);
        JLabel label = new JLabel("Please fill in your name : ");
        label.setBounds(LABEL);
        label.setFont(new Font(null, Font.BOLD, 15));

        field = new JTextField();
        field.setBounds(FIELD);
        frame.add(label);
        frame.add(field);
    }

    public void scoreVisible() {
        frame.setVisible(true);
    }

    public void setHighScore(double highScore) {
        this.highScore = highScore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            try {
                String word = field.getText();
                File file = new File("scoreboard.txt");
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter buffer = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(buffer);
                printWriter.printf("%s %.2f\n", word, this.highScore);
                printWriter.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            frame.dispose();
            owner.enableHomeMenu(true);
        }
    }
}

