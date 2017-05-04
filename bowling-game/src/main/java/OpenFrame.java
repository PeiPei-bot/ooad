import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-04-08
 */
public class OpenFrame extends Frame {

    public OpenFrame(List<Integer> tumble, int firstTumble, int secondTumble) {
        super(tumble);
        tumble.add(firstTumble);
        tumble.add(secondTumble);
    }

    @Override
    public int score() {
        return tumble.get(startingTumble
        ) + tumble.get(startingTumble + 1);
    }
}
