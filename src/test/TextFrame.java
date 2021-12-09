package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public abstract class TextFrame extends Image implements ActionListener {

    private JFrame frame;
    private JButton btn;
    private Point coordinate;

    public final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public TextFrame(String name) {
        super();
        setFrame(name);
        button();
    }

    public void setFrame(String name) {
        frame = new JFrame(name);
        frame.setSize(super.getArea());
        frame.setUndecorated(true);
        setCoordinate();
        frame.setLocation(getCoordinate());
        frame.setLayout(null);
        frame.setContentPane(new JLabel(new ImageIcon(super.img())));
    }

    public void setCoordinate() {
        int x = (screenSize.width / 2) - (frame.getWidth() / 2);
        int y = (screenSize.height / 2) - (frame.getHeight() / 2);
        this.coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return this.coordinate;
    }

    public JButton getBtn() {
        return btn;
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void button() {
        btn = new JButton("Back");
        btn.setFont(new Font("Arial", Font.ITALIC, 20));
        btn.setBounds(400, 470, 100, 30);
        btn.addActionListener(this);
        frame.add(btn);
    }

    @Override
    public abstract void content() throws IOException;
}