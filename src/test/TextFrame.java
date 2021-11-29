package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

abstract public class TextFrame implements ActionListener {

    private final JFrame frame;
    private final JButton btn;
    private Point coordinate;


    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public TextFrame(String name) {

        DisplayImage image = new DisplayImage();

        frame = new JFrame(name);
        frame.setSize(image.getArea());
        frame.setUndecorated(true);
        setCoordinate();
        frame.setLocation(getCoordinate());
        frame.setLayout(null);
        frame.setContentPane(new JLabel(new ImageIcon(image.img())));

        btn = new JButton("Back");
        btn.setFont(new Font("Arial", Font.ITALIC, 25));
        btn.setBounds(400, 470, 100, 30);
        btn.addActionListener(this);
        frame.add(btn);
        frame.setVisible(true);
    }

    public Point getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate() {
        int x = (size.width / 2) - (frame.getWidth() / 2);
        int y = (size.height / 2) - (frame.getHeight() / 2);
        this.coordinate = new Point(x, y);
    }

    public abstract void content();

    public JButton getBtn() {
        return btn;
    }

    public JFrame getFrame() {
        return frame;
    }
}
