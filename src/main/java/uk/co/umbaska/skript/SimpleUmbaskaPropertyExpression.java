package uk.co.umbaska.skript;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.co.umbaska.registrations.*;
import uk.co.umbaska.registrations.annotations.BSyntax;
import uk.co.umbaska.registrations.annotations.BSyntaxes;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

/**
 * @author Andrew Tran
 */
public abstract class SimpleUmbaskaPropertyExpression<F,T> extends PropertyExpression<F,T> implements AutoRegisteringSkriptElement, ExpressionManagerProvider, DynamicSyntax {
    private Class<T> inferredClass;
    private ExpressionManager expressionManager;

    @Override
    public String[] getSyntax() {
        return null;
    }

    @Override
    public String toString() {
        return "Umbaska Property Expression: " + getClass().getCanonicalName();
    }

    @Override
    public String toString(Event event, boolean b) {
        return toString();
    }

    @Override
    public Class<? extends T> getReturnType() {
        if (inferredClass == null){
            Type superClass = getClass().getGenericSuperclass();
            Type genericType = ((ParameterizedType)superClass).getActualTypeArguments()[1]; //Second type argument
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
        //No binding needed
        setExpr((Expression<? extends F>) expressions[0]);
        UExpression<?>[] uExpressions = Arrays.stream(expressions).map((Function<Expression<?>, ? extends UExpression<?>>) UExpression::new).toArray(UExpression[]::new);
        return init(uExpressions, i, Delay.fromKleenean(kleenean), new ParseResult(parseResult));
    }

    public boolean indexExists(final Object[] arr, final int index) {
        return index >= 0 && index < arr.length;
    }

    public boolean init(UExpression<?>[] expressions, int matchedPattern, Delay delay, ParseResult parseResult){
        return true;
    }

    @Override
    protected T[] get(Event event, F[] fs) {
        if (isUsingExpressionManager()){
            expressionManager.setEvent(event);
        }
        Object[] toReturn = null;
        Boolean implemented = false;
        detect: {
            for (Method method : getClass().getMethods()) {
                if (method.getDeclaringClass().equals(getClass())) {
                    switch (method.getName()) {
                        case "getEventValue":
                            toReturn = new Object[]{getEventValue(new EventSource(event), fs)};
                            implemented = true;
                            break detect;
                        case "getEventValues":
                            toReturn = getEventValues(new EventSource(event), fs);
                            implemented = true;
                            break detect;
                        case "getValue":
                            toReturn = new Object[]{getValue(fs)};
                            implemented = true;
                            break detect;
                        case "getValues":
                            toReturn = getValues(fs);
                            implemented = true;
                            break detect;
                    }
                }
            }
        }
        if (!implemented){
            throw new RuntimeException(String.format("Class %s does not implement getEventValue, getEventValues, getValue, or getValues.", getClass().getCanonicalName()));
        }
        //I'm not sure of a workaround, as I can't instantiate an array from a generic type
        return (T[]) toReturn;
    }

    public T getEventValue(EventSource eventSource, F[] source){
        return null;
    }
    public T[] getEventValues(EventSource eventSource, F[] source){
        return null;
    }
    public T getValue(F[] source){
        return null;
    }
    public T[] getValues(F[] source){
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
