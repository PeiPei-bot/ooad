/**
 * @author Kj Nam
 * @since 2017-04-04
 */
public class PositiveNumber {

    private int value;

    public PositiveNumber(int value) {
        if (0 > value)
            throw new RuntimeException("0 이상의 정수만 입력할 수 있습니다.");

        this.value = value;
    }

    public PositiveNumber(String value) {
        this(Integer.parseInt(value));
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 31 * result + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PositiveNumber))
            return false;

        return value == ((PositiveNumber) obj).value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}