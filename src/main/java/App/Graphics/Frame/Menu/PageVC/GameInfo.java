package App.Graphics.Frame.Menu.PageVC;

import App.Graphics.Frame.GameFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class display the info of the game.
 * It tells user the instructions and
 * how to play the game.
 */

public class GameInfo extends TextFrameView {

    private final GameFrame owner;
    private final JFrame frame;
    private final JButton btn;

    /**
     * This constructor calls its parents'
     * constructor to perform parents' attributes.
     * The value of frame and buttons are initialize from
     * its parents.
     * It executes the content method.
     *
     * @param owner Control GameFrame method whenever event is captured
     */

    public GameInfo(GameFrame owner) {
        super("Info");
        this.owner = owner;
        frame = super.getFrame();
        btn = super.getBtn();
        content();
    }

    /**
     * Whenever the button is clicked by the user,
     * this frame will be destroyed and create
     * selection game from GameFrame.
     *
     * @param e Any action made by the user
     */

    /*
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == btn) {
            frame.dispose();
            owner.enableSelectionGame(false);
        }
    }*/

    /**
     * This method display the instructions and
     * the tutorials by using HTML, p, ol and li
     * tags to perform point form and paragraph for
     * JLabel.
     * The JLabel will be displayed to the user's screen
     * with specific fonts, colours and location.
     */

    @Override
    public void content() {
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
                "<li> Press A or D to move left or right.</li>" +
                "<li> There is only one level.</li>" +
                "<li> 90 seconds gameplay.</li>" +
                "<li> Win = Survive for 90 seconds or break all the bricks.</li>" +
                "<li> Lose = No balls left.</li>" +
                "<li> Score is recorded after gameplay.</li>" +
                "</ol>" +
                "</html>";
        JLabel label = new JLabel(instruction);
        label.setBounds(10, 60, 511, 380);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("serif", Font.PLAIN, 18));
        frame.add(label);
        frame.setVisible(true);
    }

    /**
     * This method is to get the
     * GameFrame.
     *
     * @return The GameFrame where the components are created
     */

    public GameFrame getOwner() {
        return this.owner;
    }
}
