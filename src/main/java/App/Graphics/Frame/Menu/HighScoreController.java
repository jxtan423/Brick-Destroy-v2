package App.Graphics.Frame.Menu;

import App.Graphics.Frame.Menu.PageVC.HighScore;

public class HighScoreController {

    private HighScore highScore;

    public HighScoreController (HighScore highScore) {
        this.highScore = highScore;
        highScore.addClickListener(e -> back());
    }

    private void back() {
        highScore.getFrame().dispose();
        highScore.getOwner().enableSelectionGame(false);
    }
}
