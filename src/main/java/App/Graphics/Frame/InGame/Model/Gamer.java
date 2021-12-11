package App.Graphics.Frame.InGame.Model;

public class Gamer {

    private final String name;
    private final double point;

    public Gamer(String username, double score) {
        name = username;
        point = score;
    }

    public String getName() {
        return name;
    }

    public double getPoint() {
        return point;
    }
}

