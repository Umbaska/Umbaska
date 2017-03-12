import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.util.SimpleLiteral;
import ch.njol.util.Kleenean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.umbaska.registrations.annotations.BSyntax;
import uk.co.umbaska.skript.SimpleUmbaskaExpression;

/**
 * @author Andrew Tran
 */
public class SimpleUmbaskaExpressionTest {
    SimpleUmbaskaExpression<String> expression;
    @Before
    public void setup(){
        expression = new ExampleExpression();
        expression.init(new Expression[]{new SimpleLiteral<String>("test",false)}, 0, Kleenean.FALSE, null);
    }
    @Test
    public void testBindedExpressionsBindProperly(){
        Assert.assertEquals("Binded Expression is returning the wrong value", "test", expression.getValue());
    }

    @BSyntax(syntax = "example %text%", bind = {"text"})
    public static class ExampleExpression extends SimpleUmbaskaExpression<String>{
        @Override
        public String getValue() {
            return exp().getString("text");
        }
    }
}
