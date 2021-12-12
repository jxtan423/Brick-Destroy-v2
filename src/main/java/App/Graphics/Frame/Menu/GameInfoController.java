package App.Graphics.Frame.Menu;

import App.Graphics.Frame.Menu.PageVC.GameInfo;

public class GameInfoController {

    private final GameInfo gameInfo;

    /**
     *
     * @param gameInfo
     */
    public GameInfoController (GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        gameInfo.getBtn().addActionListener(e -> back());
    }

    /**
     * The gameInfoView frame will be destroyed and
     * selection game is created from GameFrame.
     */

    public void back() {
        gameInfo.getFrame().dispose();
        gameInfo.getOwner().enableSelectionGame(false);
    }
}
