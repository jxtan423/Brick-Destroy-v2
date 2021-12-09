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

    public HighScore(GameFrame owner) throws IOException {
        super("High Score");
        this.owner = owner;
        frame = super.getFrame();
        btn = super.getBtn();
        content();
    }

    @Override
    public void content() throws IOException {
        JLabel label = new JLabel("High score list");
        label.setBounds(162, 0, 300, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(label);

        JLabel normal = new JLabel("Normal Game :");
        normal.setBounds(0,30,300,30);
        normal.setForeground(Color.WHITE);
        normal.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(normal);

        JLabel special = new JLabel("Special Game :");
        special.setBounds(0,245,300,30);
        special.setForeground(Color.WHITE);
        special.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(special);

        display("normal.txt", new Point(100,60));
        display("special.txt", new Point(100,275));
    }

    private void display(String filename, Point pos) throws IOException {
        ArrayList<Gamer> list = new ArrayList<>();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        StringBuilder score = new StringBuilder();
        StringBuilder name = new StringBuilder();

        FileReader file = new FileReader(filename);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String username = scan.next();
            double point = scan.nextDouble();
            list.add(new Gamer(username, point));
        }

        list.sort(new scoreComparison());

        BufferedWriter write = new BufferedWriter(new FileWriter(filename));
        for(Gamer gamer : list) {
            write.write(gamer.getName());
            write.write(" "+gamer.getPoint());
            name.append(gamer.getName()).append("\n");
            score.append(" ").append(gamer.getPoint()).append("\n");
            write.newLine();
        }
        file.close();
        write.close();

        JTextArea player = new JTextArea(String.valueOf(name));
        player.setBackground(Color.GRAY);
        player.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        player.setFont(new Font("serif", Font.BOLD, 25));
        player.setText(name + "\n");
        player.setBounds(pos.x, pos.y,250, 165);
        frame.add(player);

        JTextArea point = new JTextArea(String.valueOf(score));
        point.setBackground(Color.GRAY);
        point.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        point.setFont(new Font("serif", Font.BOLD, 25));
        point.setText(score + "\n");
        point.setBounds(pos.x + 250, pos.y, 80, 165);
        frame.add(point);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            frame.dispose();
            owner.enableSelectionGame(false);
        }
    }
}