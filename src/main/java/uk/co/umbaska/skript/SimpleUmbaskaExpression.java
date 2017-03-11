package uk.co.umbaska.skript;

import ch.njol.skript.lang.util.SimpleExpression;
import org.bukkit.event.Event;
import uk.co.umbaska.registrations.AutoRegisteringSkriptElement;
import uk.co.umbaska.registrations.EventSource;
import uk.co.umbaska.registrations.ExpressionManagerProvider;
import uk.co.umbaska.skript.ExpressionManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Andrew Tran
 */
public abstract class SimpleUmbaskaExpression<T> extends SimpleExpression<T> implements AutoRegisteringSkriptElement, ExpressionManagerProvider {
    private Class<T> inferredClass;
    private ExpressionManager expressionManager;
    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString() {
        return "Umbaska Simple Expression: " + getClass().getCanonicalName();
    }

    @Override
    public String toString(Event event, boolean b) {
        return toString();
    }

    @Override
    public Class<? extends T> getReturnType() {
        if (inferredClass == null){
            Type superClass = getClass().getGenericSuperclass();
            Type genericType = ((ParameterizedType)superClass).getActualTypeArguments()[0];
            String className = genericType.toString().split(" ")[1];
            try {
                /* Should work hopefully ; Cross your fingers!
                 If anyone knows a better way, let me know! */
                inferredClass = (Class<T>) Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return inferredClass;
    }



    @Override
    protected T[] get(Event event) {
        if (isUsingExpressionManager()){
            expressionManager.setEvent(event);
        }
        return get(new EventSource(event));
    }



    public T[] get(EventSource eventSource){
        return null;
    }

    @Override
    public ExpressionManager getExpressionManager() {
        if (expressionManager == null){
            expressionManager = new ExpressionManager();
        }
        return expressionManager;
    }

    @Override
    public ExpressionManager exp() {
        return getExpressionManager();
    }

    @Override
    public Boolean isUsingExpressionManager() {
        return getExpressionManager() != null;
    }
}
