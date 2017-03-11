package uk.co.umbaska.skript;

import org.bukkit.event.Event;
import uk.co.umbaska.registrations.UExpression;

import java.util.HashMap;

/**
 * @author Andrew Tran
 */
public class ExpressionManager {
    private HashMap<String, UExpression> expressions = new HashMap<>();
    private Event event;

    public void set(String key, UExpression UExpression){
        expressions.put(key, UExpression);
    }

    public void bind(String key, UExpression UExpression){
        set(key, UExpression);
    }

    public UExpression getExpression(String key){
        return expressions.get(key);
    }

    public Object get(String key){
        return getExpression(key).get(event);
    }

    public Object[] getMultiple(String key){
        return getExpression(key).getMultiple(event);
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
