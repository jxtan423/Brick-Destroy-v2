package App.Graphics.Frame.Menu.Page.Controller;

import App.Graphics.Frame.Menu.Page.GameInfo;

/**
 * This class is a controller class
 * for the GameInfo.
 */

public class GameInfoController {

    private final GameInfo gameInfo;

    /**
     * This constructor is to assign the component
     * from the parameter.
     * The component's button will be added action
     * listener.
     *
     * @param gameInfo The component
     */

    public GameInfoController (GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        gameInfo.getBtn().addActionListener(e -> back());
    }

    /**
     * The gameInfo frame will be destroyed and
     * selection game is created from GameFrame.
     */

    public void back() {
        gameInfo.getFrame().dispose();
        gameInfo.getOwner().enableSelectionGame(false);
    }
}
