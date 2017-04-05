/**
 * @author Kj Nam
 * @since 2017-04-04
 */
public interface Splitter {
    String[] split(String formula);

    boolean support(String formula);
}