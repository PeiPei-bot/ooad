import java.util.ArrayList;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-04
 */
public abstract class Frame implements IFrame {
    protected List<Integer> tumble = new ArrayList();
    protected int startingTumble = 0;

    public Frame(List<Integer> tumble) {
        this.tumble = tumble;
        this.startingTumble = tumble.size();
    }

    @Override
    public abstract int score();
}
