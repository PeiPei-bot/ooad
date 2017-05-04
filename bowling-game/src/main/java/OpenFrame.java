/**
 * @author Kj Nam
 * @since 2017-04-08
 */
public class Frame {
    Roll[] rolls = new Roll[2];
    int score;

    public Frame(int firstThrow, int secondThrow) {
        score = firstThrow + secondThrow;
    }

    public void roll(int pins) {
    }

    public int score() {
        return score;
    }
}
