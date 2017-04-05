/**
 * @author Kj Nam
 * @since 2017-04-04
 */
public class DefaultSplitter implements Splitter {

    @Override
    public String[] split(String formula) {
        return formula.split(",|:");
    }

    @Override
    public boolean support(String formula) {
        return formula.matches("^\\d+(?:[,\\:]\\d+)*$|^");
    }
}
