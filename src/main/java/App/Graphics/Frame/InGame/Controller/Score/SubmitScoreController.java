package App.Graphics.Frame.InGame.Controller.Score;

import App.Graphics.Frame.InGame.View.Score.SubmitScore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * This class is a controller class
 * for SubmitScore.
 */

public class SubmitScoreController {

    private final SubmitScore submitScore;

    /**
     * This constructor is to assign the component
     * from the parameter.
     * The component's button will be added action
     * listener.
     *
     * @param submitScore The component
     */
    public SubmitScoreController(SubmitScore submitScore) {
        this.submitScore = submitScore;
        submitScore.getBtn().addActionListener(e -> submit());
    }

    /**
     * This method will captured the action of the user
     * on clicking the button using the mouse.
     * If the user clicks on the button, the name
     * and the score will be saved into a txt file in
     * a specific format.
     * After saving, the frame will be destroyed itself
     * and called HomeMenu to be displayed on user's screen.
     *
     */

    private void submit() {
        try {
            String word = submitScore.getField().getText();
            File file = new File(submitScore.getFilename());
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            printWriter.printf("%s %.2f\n", word, submitScore.getHighScore());
            printWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        submitScore.getFrame().dispose();
        submitScore.getOwner().enableHomeMenu(submitScore.isFromNormalGame());
    }
}
