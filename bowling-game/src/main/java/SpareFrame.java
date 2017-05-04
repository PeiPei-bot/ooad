import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-04
 */
public class SpareFrame extends Frame {

    public SpareFrame(List<Integer> tumble, int firstTumble, int secondTumble) {
        super(tumble);
        tumble.add(firstTumble);
        tumble.add(secondTumble);
    }

    private int nextBall() {
        return tumble.get(startingTumble + 2);
    }

    @Override
    public int score() {
        return 10 + nextBall();
    }
}
