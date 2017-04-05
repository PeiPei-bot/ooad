import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-04-05
 */
class StringCalculator {
    List<Splitter> splitters =
            Arrays.asList(new DefaultSplitter(), new CustomSplitter());

    public int add(String expression) {
        if (isEmpty(expression))
            return 0;

        String [] numbers = split(expression);
        List<PositiveNumber> list = convert(numbers);
        return sum(list);
    }

    private String[] split(String expression) {
        for (Splitter splitter : splitters) {
            if (splitter.support(expression))
                return splitter.split(expression);
        }
        throw new RuntimeException();
    }

    private boolean isEmpty(String expression) {
        return  (expression == null || expression.isEmpty());
    }

    private List<PositiveNumber> convert(String [] textNumbers) {
        List<PositiveNumber> numbers = new ArrayList<>();
        for (String number : textNumbers) {
            PositiveNumber positiveNumber = new PositiveNumber(number);
            numbers.add(positiveNumber);
        }
        return numbers;
    }

    private int sum(List<PositiveNumber> numbers) {
        int sum = 0;

        for (PositiveNumber number : numbers) {
            sum += number.getValue();
        }

        return sum;
    }
}
