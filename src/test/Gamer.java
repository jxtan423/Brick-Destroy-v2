package test;

import java.util.Comparator;

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

class scoreComparison implements Comparator<Gamer> {

    @Override
    public int compare(Gamer o1, Gamer o2) {
        return Double.compare(o2.getPoint(), o1.getPoint());
    }
}
