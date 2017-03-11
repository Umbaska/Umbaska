package uk.co.umbaska.skript;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.co.umbaska.registrations.AutoRegisteringSkriptElement;
import uk.co.umbaska.registrations.EventSource;
import uk.co.umbaska.registrations.ExpressionManagerProvider;
import uk.co.umbaska.registrations.UExpression;
import uk.co.umbaska.skript.ExpressionManager;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Function;

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
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        UExpression<?>[] uExpressions = Arrays.stream(expressions).map((Function<Expression<?>, ? extends UExpression<?>>) UExpression::new).toArray(UExpression[]::new);
        return init(uExpressions, i, Delay.fromKleenean(kleenean), new ParseResult(parseResult));
    }

    public boolean init(UExpression<?>[] expressions, int matchedPattern, Delay delay, ParseResult parseResult){
        return true;
    }

    @Override
    protected T[] get(Event event) {
        if (isUsingExpressionManager()){
            expressionManager.setEvent(event);
        }
        Object[] toReturn = null;
        Boolean implemented = false;
        //I'm not sure of a workaround, as I can't instantiate an array from a generic type
        for (Method method : getClass().getMethods()){
            if (method.getDeclaringClass().equals(getClass())){
                switch(method.getName()){
                    case "getEventValue":
                        toReturn = new Object[]{getEventValue(new EventSource(event))};
                        implemented = true;
                        break;
                    case "getEventValues":
                        toReturn = (Object[]) getEventValues(new EventSource(event));
                        implemented = true;
                        break;
                    case "getValue":
                        toReturn = new Object[]{getValue()};
                        implemented = true;
                        break;
                    case "getValues":
                        toReturn = (Object[]) getValues();
                        implemented = true;
                        break;
                }
            }
        }
        if (!implemented){
           throw new RuntimeException(String.format("Class %s does not implement getEventValue, getEventValues, getValue, or getValues.", getClass().getCanonicalName()));
        }
        return (T[]) toReturn;
    }



    public T getEventValue(EventSource eventSource){
        return null;
    }
    public T getEventValues(EventSource eventSource){
        return null;
    }
    public T getValue(){
        return null;
    }
    public T getValues(){
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
