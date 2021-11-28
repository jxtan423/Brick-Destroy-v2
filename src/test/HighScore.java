package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScore extends JComponent implements ActionListener {

    private JFrame frame;
    private GameFrame owner;
    private JButton btn;

    public HighScore(GameFrame owner) throws FileNotFoundException {

        ArrayList<Score> list = new ArrayList<>();

        this.owner = owner;

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        final int FRAME_WIDTH = 511;
        final int FRAME_HEIGHT = 511;

        StringBuilder score = new StringBuilder();
        StringBuilder name = new StringBuilder();
        String line = "";

        frame = new JFrame("HighScore List");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setUndecorated(true);
        frame.setLocation((size.width / 2) - (frame.getWidth() / 2), (size.height / 2) - (frame.getHeight() / 2));
        frame.setLayout(null);

        JLabel label = new JLabel("High score list :");
        label.setBounds(162,0,300,30);
        label.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(label);

        btn = new JButton("Back");
        btn.setBounds(400, 470, 100, 30);
        btn.addActionListener(this);
        frame.add(btn);

        frame.setVisible(true);

        FileReader file = new FileReader("scoreboard.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String username = scan.next();
            Double point = scan.nextDouble();
            name.append(username).append("\n");
            score.append(point).append("\n");
        }

        JTextArea area = new JTextArea(String.valueOf(name));
        area.setFont(new Font("Arial", Font.BOLD, 27));
        area.setText(name + "\n");
        area.setBounds(100,40,200,160);
        frame.add(area);

        JTextArea area1 = new JTextArea(String.valueOf(score));
        area1.setFont(new Font("Arial", Font.BOLD, 27));
        area1.setText(score + "\n");
        area1.setBounds(350,40,70,160);
        frame.add(area1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn) {
            frame.dispose();
            owner.setUndecorated(true);
            owner.enableSelectionGame(false);
        }
    }
}