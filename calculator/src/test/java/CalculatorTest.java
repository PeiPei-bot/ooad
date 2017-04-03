import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-04-03
 */
public class CalculatorTest {
    private StringCalculator calculator;

    @Before
    public void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    public void 기본_구분자로_구분하여_더한다() {
        assertEquals(0,       calculator.add(""));
        assertEquals((1+2),   calculator.add("1,2"));
        assertEquals((1+2+3), calculator.add("1,2,3"));
        assertEquals((1+2+3), calculator.add("1,2:3"));
    }

    @Test
    public void 커스텀_구분자로_구분하여_더한다() {
        // FIXME 요구사항에 기능에 없는 대한 테스트이다.
        // 지나친 테스트는 유지보수를 더 힘들게 한다.
        // 꼭 필요한 테스트만 작성하는 것도 중요한 능력이다.
        // assertEquals(0, calculator.add("//;\n"));
        assertEquals((1+2),     calculator.add("//;\n1;2"));
        assertEquals((1+2+3),   calculator.add("//,\n1,2,3"));
        assertEquals((1+2+3+4), calculator.add("//@\n1@2@3@4@"));
    }

    @Test
    public void javaAPITest() {
        assertTrue("//;\n".startsWith("//"));
        assertTrue("//;\n".endsWith("\n"));
    }

    @Test(expected = RuntimeException.class)
    public void 음수를_입력하면_런타임_예외가_발생한다() {
        assertEquals((1+2+3), calculator.add("A,B:C"));
        assertEquals((1+2+3), calculator.add("-1,-1:-1"));
        assertEquals((1+2+3), calculator.add("-1,2,3"));

        assertEquals((1+2+3), calculator.add("//;\nA;B;3"));
        assertEquals((1+2+3), calculator.add("//;\n1;C;3"));
        assertEquals((1+2+3), calculator.add("//,\n1,-3,;3"));
    }

    class StringCalculator {
        public int add(String formula) {
            // 기저사례. 공식이 없다
            if (formula == null)
                return -1;

            if (formula.isEmpty())
                return 0;

            String [] numbers = null;

            Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(formula);

            if (matcher.find()) {
                String customDelimiter = matcher.group(1);
                numbers =  matcher.group(2).split(customDelimiter);
            }

            if (numbers == null) {
                numbers = formula.split(",|:");
            }

            int sum = 0;

            for (String each : numbers) {
                sum += Integer.parseInt(each);
            }

            return sum;
        }
    }
}
