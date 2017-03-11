package uk.co.umbaska.registrations;

import org.bukkit.event.Event;
import uk.co.umbaska.registrations.annotations.Syntax;
import uk.co.umbaska.registrations.annotations.Syntaxes;

/**
 * @author Andrew Tran
 */
@Syntaxes({
    @Syntax({"test",""})
})
public class UExpression<T>{
    ch.njol.skript.lang.Expression<T> skriptExpression;
    public UExpression(ch.njol.skript.lang.Expression<T> skriptExpression) {
        this.skriptExpression = skriptExpression;
    }

    public ch.njol.skript.lang.Expression getSkriptExpression() {
        return skriptExpression;
    }

    public void setSkriptExpression(ch.njol.skript.lang.Expression skriptExpression) {
        this.skriptExpression = skriptExpression;
    }

    public T get(Event event){
        return skriptExpression.getSingle(event);
    }

    public T[] getMultiple(Event event){
        return skriptExpression.getAll(event);
    }
}
