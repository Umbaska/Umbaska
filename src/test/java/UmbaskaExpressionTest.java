import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.util.SimpleLiteral;
import ch.njol.util.Kleenean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.umbaska.registrations.BindedSyntaxException;
import uk.co.umbaska.registrations.annotations.BSyntax;
import uk.co.umbaska.skript.UmbaskaExpression;

/**
 * @author Andrew Tran
 */
public class UmbaskaExpressionTest {
    UmbaskaExpression<String> expression = new ExampleExpression();
    UmbaskaExpression<String> invalidExpression = new InvalidBindExampleExpression();
    UmbaskaExpression<String> omittedExpression = new OmittedBindExampleExpression();

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Before
    public void setup(){
        expression.init(new Expression[]{new SimpleLiteral<String>("test",false)}, 0, Kleenean.FALSE, null);
    }
    @Test
    public void testBindedExpressionsBindProperly(){
        Assert.assertEquals("Binded Expression returned the wrong value", "test", expression.getValue());
    }

    @Test
    public void testInvalidBind(){
        exception.expect(BindedSyntaxException.class);
        invalidExpression.init(new Expression[]{new SimpleLiteral<String>("test2",false)}, 0, Kleenean.FALSE, null);
        Assert.assertEquals("Invalid (on purpose) Binded Expression returned the wrong value", "test2", invalidExpression.getValue());
    }

    @Test
    public void omittedInvalidBind() {
        omittedExpression.init(new Expression[]{new SimpleLiteral<String>("test3", false)}, 0, Kleenean.FALSE, null);
        Assert.assertEquals("Omitted Binded Expression returned the wrong value", "test3", omittedExpression.getValue());
    }
    @Test
    public void testAutomaticGetReturnDetection(){
        Assert.assertEquals("Expression is returning the wrong type", String.class, expression.getReturnType());
    }

    @BSyntax(syntax = "example %text%", bind = {"text", "-nonexistant"})
    public static class OmittedBindExampleExpression extends UmbaskaExpression<String> {
        @Override
        public String getValue() {
            return exp().getString("text");
        }
    }

    @BSyntax(syntax = "example %text%", bind = {"text", "nonexistant"})
    public static class InvalidBindExampleExpression extends UmbaskaExpression<String> {
        @Override
        public String getValue() {
            return exp().getString("text");
        }
    }

    @BSyntax(syntax = "example %text%", bind = {"text"})
    public static class ExampleExpression extends UmbaskaExpression<String> {
        @Override
        public String getValue() {
            return exp().getString("text");
        }
    }
}
