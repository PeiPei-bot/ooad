import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-04-08
 */
public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void canRoll() {
        game.roll(0);
    }

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i< rolls ; i++) {
            game.roll(pins);
        }
    }

    @Test
    public void gutterGame() {
        rollMany(20, 0);
        assertThat(game.score(), is(0));
    }

    @Test
    public void allOnes() {
        rollMany(20, 1);
        assertThat(game.score(), is(20));
    }

    // allTwo 는 뻔히 작동할 것이므로 굳이 테스트를 작성할 필요 없다

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    @Test
    public void oneSpare() {
        rollSpare();
        game.roll(3);
        rollMany(17, 0);
        assertThat(game.score(), is(16));
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    public void oneStrike() {
        rollStrike();
        game.roll(5);
        game.roll(3);
        rollMany(16, 0);
        assertThat(game.score(), is(26));
    }

    @Test
    public void perfectGame() {
        rollMany(12, 10);
        assertThat(game.score(), is(300));
    }

    // 아래는 클래스로 분리 된 디자인을 테스트 하기 위한 코드

    @Test
    public void threes() {
        manyOpenFrames(10, 3, 3);
        assertThat(game.frameScore(), is(60));
    }

    @Test
    public void gutter() {
        manyOpenFrames(10, 0, 0);
        assertThat(game.frameScore(), is(0));
    }

    @Test
    public void spare() {
        game.spare(4, 6);
        game.openFrame(3, 5);
        manyOpenFrames(8, 0, 0);
        assertThat(game.frameScore(), is(21));
    }

    @Test
    public void spare2() {
        game.spare(4, 6);
        game.openFrame(5, 3);
        manyOpenFrames(8, 0, 0);
        assertThat(game.frameScore(), is(23));
    }

    @Test
    public void strike() {
        game.strike();
        game.openFrame(5, 3);
        manyOpenFrames(8, 0, 0);
        assertThat(game.frameScore(), is(26));
    }

    private void manyOpenFrames(int count, int firstThrow, int secondThrow) {
        for (int frameNumber = 0; frameNumber < count; frameNumber++)
            game.openFrame(firstThrow, secondThrow);
    }
}
