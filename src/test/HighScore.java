package test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScore extends TextFrame {

    private final GameFrame owner;
    private final JFrame frame;
    private final JButton btn;

    public HighScore(GameFrame owner) {
        super("High Score");
        this.owner = owner;
        frame = super.getFrame();
        btn = super.getBtn();
        content();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            frame.dispose();
            owner.enableSelectionGame(false);
        }
    }

    @Override
    public void content() {
        ArrayList<Score> list = new ArrayList<>();
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        StringBuilder score = new StringBuilder();
        StringBuilder name = new StringBuilder();
        String line = "";

        JLabel label = new JLabel("High score list :");
        label.setBounds(162, 0, 300, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(label);

        try {
            FileReader file = new FileReader("scoreboard.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String username = scan.next();
                Double point = scan.nextDouble();
                name.append(username).append("\n");
                score.append(point).append("\n");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        JTextArea player = new JTextArea(String.valueOf(name));
        player.setBackground(Color.GRAY);
        player.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        player.setFont(new Font("serif", Font.BOLD, 25));
        player.setText(name + "\n");
        player.setBounds(100, 40, 250, 165);
        frame.add(player);

        JTextArea point = new JTextArea(String.valueOf(score));
        point.setBackground(Color.GRAY);
        point.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        point.setFont(new Font("serif", Font.BOLD, 25));
        point.setText(score + "\n");
        point.setBounds(350, 40, 60, 165);
        frame.add(point);

        frame.setVisible(true);
    }
}