package uk.co.umbaska.skript;

import ch.njol.skript.lang.*;
import ch.njol.skript.lang.Expression;
import ch.njol.util.Kleenean;
import uk.co.umbaska.registrations.EventSource;
import uk.co.umbaska.registrations.UExpression;

/**
 * @author Andrew Tran
 */
public class TestExpression extends SimpleUmbaskaExpression<Boolean> {
    @Override
    public Boolean[] get(EventSource eventSource) {
        String value = (String) exp().get("value");
        return new Boolean[]{Boolean.valueOf(value)};
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        exp().bind("value", new UExpression<>((Expression<String>) expressions[0]));
        return true;
    }
}
