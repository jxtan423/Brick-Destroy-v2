package App.Graphics.Frame.InGame;

import App.Graphics.Frame.GameFrame;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * This class inherits Score class
 * where it has its parents class
 * attributes.
 * This class is to display the submit
 * form after the game where user has to
 * enter the name once the game is completed
 * successfully.
 */

public class SubmitScore extends Score {

    private final JFrame frame;
    private JTextField field;
    private final GameFrame owner;

    private final String filename;
    private double highScore;
    private boolean isFromNormalGame;

    /**
     * This constructor will call its parents'
     * constructor to get the parents attributes.
     * The frame and button are assigned by getting the
     * frame from parents class.
     * Content and checkGame will be executed.
     *
     * @param owner Control GameFrame method whenever event is captured
     * @param filename The filename to be read
     */

    public SubmitScore(GameFrame owner, String filename) {
        super("Submit Score", "Submit");
        this.owner = owner;
        this.filename = filename;
        frame = super.getFrame();
        content();
        checkGame();
        new SubmitScoreController(this);
    }

    /**
     * This method is to check the filename
     * and will assign a boolean value based on the
     * filename to identify which game the user plays.
     */

    private void checkGame() {
        HashMap<String, Boolean> hM = new HashMap<>();
        hM.put("src/main/resources/normal.txt", Boolean.TRUE);
        hM.put("src/main/resources/special.txt", Boolean.FALSE);
        isFromNormalGame = hM.get(filename);
    }

    /**
     * This method is to add label and JTextField to be
     * displayed inside the frame where JTextField
     * enable user to enter the name.
     */

    @Override
    public void content() {
        Rectangle LABEL = new Rectangle(10, 0, 250, 30);
        Rectangle FIELD = new Rectangle(50, 35, 150, 20);
        JLabel label = new JLabel("Please fill in your name : ");
        label.setBounds(LABEL);
        label.setFont(new Font(null, Font.BOLD, 15));

        field = new JTextField();
        field.setBounds(FIELD);
        frame.add(label);
        frame.add(field);
    }

    /**
     * This method is to make this frame visible
     * on user's screen.
     */

    public void scoreVisible() {
        frame.setVisible(true);
    }

    /**
     * Set the high score
     * @param highScore The total score scored by the user
     */

    public void setHighScore(double highScore) {
        this.highScore = highScore;
    }

    public JTextField getField() {
        return field;
    }

    /**
     * This method is to get the
     * GameFrame.
     *
     * @return The GameFrame where the components are created
     */

    public GameFrame getOwner() {
        return owner;
    }

    public String getFilename() {
        return filename;
    }

    public double getHighScore() {
        return highScore;
    }

    public boolean isFromNormalGame() {
        return isFromNormalGame;
    }
}

