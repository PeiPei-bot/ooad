import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-04
 */
public class StrikeFrame extends Frame {

    public StrikeFrame(List<Integer> tumble) {
        super(tumble);
        tumble.add(10);
    }
    @Override
    public int score() {
        return 10 + firstFollowingBall() + secondFollowingBall();
    }

    private int firstFollowingBall() {
        return tumble.get(startingTumble + 1);
    }

    private int secondFollowingBall() {
        return tumble.get(startingTumble + 2);
    }
}
