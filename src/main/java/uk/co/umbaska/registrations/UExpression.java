package uk.co.umbaska.registrations;

import org.bukkit.event.Event;

/**
 * Wraps an {@link ch.njol.skript.lang.Expression}
 * @author Andrew Tran
 */
public class UExpression<T>{
    ch.njol.skript.lang.Expression<T> skriptExpression;

    /**
     * Constructs an {@link UExpression} with a specified {@link ch.njol.skript.lang.Expression}
     * @param skriptExpression the {@link ch.njol.skript.Skript} expression to register
     */
    public UExpression(ch.njol.skript.lang.Expression<T> skriptExpression) {
        this.skriptExpression = skriptExpression;
    }

    /**
     * Gets the {@link ch.njol.skript.Skript} {@link ch.njol.skript.lang.Expression}
     * @return the {@link ch.njol.skript.Skript} expression
     */
    public ch.njol.skript.lang.Expression getSkriptExpression() {
        return skriptExpression;
    }

    /**
     * Sets the {@link ch.njol.skript.Skript} expression
     * @param skriptExpression the expression to set
     */
    public void setSkriptExpression(ch.njol.skript.lang.Expression skriptExpression) {
        this.skriptExpression = skriptExpression;
    }

    /**
     * Gets the value for a specified event
     * @param event the event to get the value from
     * @return the value of the expression from an event
     */
    public T get(Event event){
        return skriptExpression.getSingle(event);
    }

    /**
     * Gets the values for a specified event
     * @param event the event to get the values from
     * @return the values of the expression from an event
     */
    public T[] getMultiple(Event event){
        return skriptExpression.getAll(event);
    }
}
