package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public abstract class Score extends TextFrame {

    private final JFrame frame;
    private final JButton btn;

    private final Dimension screenSize = super.screenSize;

    public Score(String name, String buttonText) {
        super(name);
        frame = super.getFrame();
        btn = super.getBtn();
        frame.setContentPane(new JLabel());
        frameReformat();
        buttonReformat(buttonText);
    }

    public void frameReformat() {
        Dimension size = new Dimension(250, 100);
        frame.setSize(size);
        Point FRAME_COORDINATES = new Point(FRAME_X(), FRAME_Y());
        frame.setLocation(FRAME_COORDINATES);
    }

    public void buttonReformat(String buttonText) {
        Rectangle BTN = new Rectangle(75, 65, 100, 30);
        btn.setFont(new Font("Arial", Font.ITALIC, 16));
        btn.setText(buttonText);
        btn.setBounds(BTN);
        frame.add(btn);
    }

    private int FRAME_X() {
        return (screenSize.width / 2) - (frame.getWidth() / 2);
    }

    private int FRAME_Y() {
        return screenSize.height / 2;
    }

    @Override
    public abstract void content();
}
