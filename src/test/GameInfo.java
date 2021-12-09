package test;

import javax.imageio.ImageIO;
import javax.swing.*;
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
        try {
            img = ImageIO.read(getClass().getResourceAsStream("InfoBrick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new JLabel(new ImageIcon(img)));
        frame.add(btn);
        frame.setVisible(true);
    }
}
