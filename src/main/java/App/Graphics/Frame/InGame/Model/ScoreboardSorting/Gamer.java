package App.Graphics.Frame.InGame.Model.ScoreboardSorting;

/**
 * This class is to store and deal
 * with the user's name and the
 * score.
 */

public class Gamer {

    private final String name;
    private final double point;

    /**
     * This constructor assigned the variables
     * from the parameters.
     *
     * @param username The name of the user inputted
     * @param score The score that user gained
     */

    public Gamer(String username, double score) {
        name = username;
        point = score;
    }

    /**
     * @return The name of the user inputted
     */

    public String getName() {
        return name;
    }

    /**
     * @return The score that user gained
     */
    public double getPoint() {
        return point;
    }
}

