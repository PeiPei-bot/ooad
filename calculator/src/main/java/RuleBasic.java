/**
 * @author Kj Nam
 * @since 2017-04-04
 */
public class RuleBasic implements Rule {

    @Override
    public String[] split(String formula) {
        return formula.split(",|:");
    }
}
