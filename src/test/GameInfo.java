package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class GameInfo extends TextFrame implements ActionListener {

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
        if(e.getSource() == btn) {
            frame.dispose();
            owner.enableSelectionGame(false);
        }
    }

    @Override
    public void content() {
    }
}
