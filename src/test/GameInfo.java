package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameInfo extends TextFrame {

    private final GameFrame owner;
    private final JFrame frame;
    private final JButton btn;

    private BufferedImage img;

    public GameInfo(GameFrame owner) {
        super("Info");
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
        /*try {
            img = ImageIO.read(getClass().getResourceAsStream("InfoBrick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new JLabel(new ImageIcon(img)));
        frame.add(btn);*/
        String instruction = "<html>" +
                "<p>Normal Game</p>" +
                "<ol type = 1>" +
                "<li> Press A or D to move left or right.</li>" +
                "<li> There are 4 levels in total.</li>" +
                "<li> Win = Break all the bricks.</li>" +
                "<li> Lose = No balls left.</li>" +
                "<li> Score is recorded after each level.</li>" +
                "</ol>" +
                "<p>Special Game</p>" +
                "<ol type = 1>" +
                "<li>Press A or D to move left or right.</li>" +
                "<li>There is only one level.</li>" +
                "<li>90 seconds gameplay.</li>" +
                "<li>Win = Survive for 90 seconds or break all the bricks.</li>" +
                "<li>Lose = No balls left.</li>" +
                "<li>Score is recorded after gameplay.</li>" +
                "</ol>" +
                "</html>";
        JLabel label = new JLabel(instruction);
        label.setBounds(10, 60, 511, 380);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("serif", Font.BOLD, 18));
        frame.add(label);
        frame.setVisible(true);
    }
}
