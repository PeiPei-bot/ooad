import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        // FIXME 요구사항에 없는 조건에 대한 테스트이다.
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
        assertEquals((1+2+3), calculator.add("-1,-1:-1"));
    }

    class StringCalculator {
        public int add(String formula) {
            // 기저사례1. null 을 입력받는다
            if (formula == null)
                return -1;

            // 기저사례2. 빈 공식을 입력받는다
            if (formula.isEmpty())
                return 0;

            List<DisplayNumber> numbers = extractNumbers(formula);
            return addAll(numbers);
        }

        private List<DisplayNumber> extractNumbers(String formula) {
            Rule rule = decideRules(formula);
            String [] textNumbers = rule.split(formula);

            List<DisplayNumber> numbers = convert(textNumbers);
            return numbers;
        }

        private Rule decideRules(String formula) {
            Rule rule;

            if (formula.startsWith("//")) {
                rule = new RuleCustom();
            } else {
                rule = new RuleBasic();
            }
            return rule;
        }

        private List<DisplayNumber> convert(String [] textNumbers) {
            List<DisplayNumber> numbers = new ArrayList<>();
            for (String number : textNumbers) {
                DisplayNumber displayNumber = new DisplayNumber(number);
                numbers.add(displayNumber);
            }
            return numbers;
        }

        private int addAll(List<DisplayNumber> numbers) {
            int sum = 0;

            for (DisplayNumber number : numbers) {
                sum += number.getValue();
            }

            return sum;
        }
    }
}
