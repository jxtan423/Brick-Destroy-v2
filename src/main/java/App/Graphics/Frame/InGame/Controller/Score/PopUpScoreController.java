package App.Graphics.Frame.InGame.Controller.Score;

import App.Graphics.Frame.InGame.View.Score.PopUpScore;

/**
 * This class is a controller class
 * for PopUpScore.
 */

public class PopUpScoreController {

    private final PopUpScore popUpScore;

    /**
     * This constructor is to assign the component
     * from the parameter.
     * The component's button will be added action
     * listener.
     *
     * @param popUpScore The component
     */

    public PopUpScoreController(PopUpScore popUpScore) {
        this.popUpScore = popUpScore;
        popUpScore.getBtn().addActionListener(e -> carryOn());
    }

    /**
     * If user press "Continue" button, this
     * frame will be destroyed and execute game method
     * at GameFrame class to continue gaming for
     * next level.
     */

    private void carryOn() {
        popUpScore.getFrame().dispose();
        popUpScore.getOwner().game();
    }
}
