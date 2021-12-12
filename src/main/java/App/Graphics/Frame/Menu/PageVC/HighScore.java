package App.Graphics.Frame.Menu.PageVC;

import App.Graphics.Frame.GameFrame;
import App.Graphics.Frame.InGame.Model.Gamer;
import App.Graphics.Frame.InGame.ScoreComparison;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to display the top 5 players
 * scoreboard for normal game and special game.
 */

public class HighScore extends TextFrameView {

    private final GameFrame owner;
    private final JFrame frame;
    private final JButton btn;

    /**
     * This constructor calls parent class
     * and get the frame and button from parent
     * class.
     * The content of the frame will be displayed.
     *
     * @param owner Control GameFrame method whenever event is captured
     * @throws IOException Improper data will be ignored
     */

    public HighScore(GameFrame owner) throws IOException {
        super("High Score");
        this.owner = owner;
        frame = super.getFrame();
        btn = super.getBtn();
        content();
    }

    /**
     * Create labels and set location, fonts and colour
     * respectively.
     * Labels are then added to the frame.
     *
     * @throws IOException Improper data will be ignored
     */

    @Override
    public void content() throws IOException {
        JLabel label = new JLabel("High score list");
        label.setBounds(162, 0, 300, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(label);

        JLabel normal = new JLabel("Normal Game :");
        normal.setBounds(0, 30, 300, 30);
        normal.setForeground(Color.WHITE);
        normal.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(normal);

        JLabel special = new JLabel("Special Game :");
        special.setBounds(0, 245, 300, 30);
        special.setForeground(Color.WHITE);
        special.setFont(new Font("Arial", Font.PLAIN, 27));
        frame.add(special);

        display("src/main/resources/normal.txt", new Point(100, 60));
        display("src/main/resources/special.txt", new Point(100, 275));
    }

    /**
     * This method reads the txt file and sort it in descending
     * order based on user's score in an array.
     * The sorted array overwrites the content under the same txt file.
     * The txt files will be displayed on user's screen with
     * sorted name and score.
     * The location, size and fonts for txt files are set.
     *
     * @param filename The txt file name and directory
     * @param pos The position of txt file that will be displayed
     * @throws IOException Improper data will be ignored
     */

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

        list.sort(new ScoreComparison());

        BufferedWriter write = new BufferedWriter(new FileWriter(filename));
        for (Gamer gamer : list) {
            write.write(gamer.getName());
            write.write(" " + gamer.getPoint());
            name.append(gamer.getName()).append("\n");
            score.append(" ").append(gamer.getPoint()).append("\n");
            write.newLine();
        }
        file.close();
        write.close();

        JTextArea player = new JTextArea(String.valueOf(name));
        player.setOpaque(false);
        player.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        player.setForeground(Color.WHITE);
        player.setFont(new Font("serif", Font.BOLD, 25));
        player.setText(name + "\n");
        player.setBounds(pos.x, pos.y, 250, 165);
        frame.add(player);

        JTextArea point = new JTextArea(String.valueOf(score));
        point.setOpaque(false);
        point.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        point.setForeground(Color.WHITE);
        point.setFont(new Font("serif", Font.BOLD, 25));
        point.setText(score + "\n");
        point.setBounds(pos.x + 250, pos.y, 80, 165);
        frame.add(point);

        frame.setVisible(true);
    }

    /**
     * If user presses the "Back" button,
     * this frame will be destroyed and
     * selection game is displayed after.
     *
     * @param e Capture any event from this class
     */
    /*
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            frame.dispose();
            owner.enableSelectionGame(false);
        }
    }*/
    public GameFrame getOwner() {
        return this.owner;
    }

    public void addClickListener(ActionListener e) {
        btn.addActionListener(e);
    }
}