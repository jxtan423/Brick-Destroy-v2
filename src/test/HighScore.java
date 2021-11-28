package test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScore {

    private JFrame frame;
    private GameFrame owner;

    public HighScore(GameFrame owner) throws IOException {

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
        frame.setLocation((size.width / 2) - (frame.getWidth() / 2), size.height / 4);
        frame.setLayout(null);

        JLabel label = new JLabel("High score list :");
        label.setBounds(162,0,300,30);
        label.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(label);

        frame.setVisible(true);

        FileReader file = new FileReader("scoreList.txt");

        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            Double point = scan.nextDouble();
            String username = scan.next();
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

}
