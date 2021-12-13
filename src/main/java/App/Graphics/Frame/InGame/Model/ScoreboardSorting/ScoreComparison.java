package App.Graphics.Frame.InGame.Model.ScoreboardSorting;

import App.Graphics.Frame.InGame.Model.ScoreboardSorting.Gamer;

import java.util.Comparator;

/**
 * This class is to compare the score between
 * users.
 */

public class ScoreComparison implements Comparator<Gamer> {

    /**
     * This method is to compare which gamer
     * has the highest score.
     *
     * @param o1 First gamer
     * @param o2 Second gamer
     * @return The highest score among both gamers
     */
    @Override
    public int compare(Gamer o1, Gamer o2) {
        return Double.compare(o2.getPoint(), o1.getPoint());
    }
}
