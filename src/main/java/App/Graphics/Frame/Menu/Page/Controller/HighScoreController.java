package App.Graphics.Frame.Menu.Page.Controller;

import App.Graphics.Frame.Menu.Page.HighScore;

/**
 * This class is a controller class
 * for HighScore.
 */

public class HighScoreController {

    private final HighScore highScore;

    /**
     * This constructor is to assign the component
     * from the parameter.
     * The component's button will be added action
     * listener.
     *
     * @param highScore The component
     */

    public HighScoreController (HighScore highScore) {
        this.highScore = highScore;
        highScore.getBtn().addActionListener(e -> back());
    }

    /**
     * The HighScore frame will be destroyed and
     * selection game is created from GameFrame.
     */

    private void back() {
        highScore.getFrame().dispose();
        highScore.getOwner().enableSelectionGame(false);
    }
}