/**
 * @author Kj Nam
 * @since 2017-04-04
 */
public class DisplayNumber {

    private int value;

    public DisplayNumber(int value) {
        setValue(value);
    }

    public DisplayNumber(String value) {
        try {
            setValue(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            throw new RuntimeException("0 이상의 정수만 입력할 수 있습니다.");
        }
    }

    // 프라이빗 접근 제어. 세터를 내부적으로만 사용
    private void setValue(int value) {
        if (value >= 0)
            this.value = value;
        else
            throw new RuntimeException("0 이상의 정수만 입력할 수 있습니다.");
    }

    public int getValue() {
        return value;
    }
}
