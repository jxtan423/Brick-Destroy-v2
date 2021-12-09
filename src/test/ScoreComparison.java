package test;

import java.util.Comparator;

public class ScoreComparison implements Comparator<Gamer> {

    @Override
    public int compare(Gamer o1, Gamer o2) {
        return Double.compare(o2.getPoint(), o1.getPoint());
    }
}
