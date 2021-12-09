package test;

import javax.swing.*;
import java.awt.event.*;

public class GameInfo extends TextFrame {

    private final GameFrame owner;
    private final JFrame frame;
    private final JButton btn;

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
        frame.setVisible(true);
    }
}
