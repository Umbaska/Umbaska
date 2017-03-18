package uk.co.umbaska.skript;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.lang.util.SimpleLiteral;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.co.umbaska.modules.UmbaskaModule;
import uk.co.umbaska.registrations.*;
import uk.co.umbaska.registrations.annotations.BSyntax;
import uk.co.umbaska.registrations.annotations.BSyntaxes;
import uk.co.umbaska.skript.ExpressionManager;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

/**
 * An Abstract Class that can be implemented to make a new {@link SimpleExpression}
 * @author Andrew Tran
 * @see UmbaskaModule#registerSyntaxes()
 */
public abstract class UmbaskaExpression<T> extends SimpleExpression<T> implements AutoRegisteringSkriptElement, ExpressionManagerProvider, DynamicSyntax {
    private Class<T> inferredClass;
    private ExpressionManager expressionManager;

    @Override
    public String[] getSyntax() {
        return null;
    }

    @Override
    public boolean isSingle() {
        Boolean implemented = false;
        Boolean isSingle = null;
        detect: {
            for (Method method : getClass().getMethods()) {
                if (method.getDeclaringClass().equals(getClass())) {
                    switch (method.getName()) {
                        case "getEventValue":
                            implemented = true;
                            isSingle = true;
                            break detect;
                        case "getEventValues":
                            implemented = true;
                            isSingle = false;
                            break detect;
                        case "getValue":
                            implemented = true;
                            isSingle = true;
                            break detect;
                        case "getValues":
                            implemented = true;
                            isSingle = false;
                            break detect;
                    }
                }
            }
        }
        if (!implemented){
            throw new RuntimeException(String.format("Class %s does not implement getEventValue, getEventValues, getValue, or getValues.", getClass().getCanonicalName()));
        }
        return isSingle;
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
        //Check for Binded Syntaxes
        if (getClass().isAnnotationPresent(BSyntaxes.class) || getClass().isAnnotationPresent(BSyntax.class)){
            BSyntax[] bindedSyntaxes = new BSyntax[0];
            if (getClass().isAnnotationPresent(BSyntaxes.class)){
                bindedSyntaxes = getClass().getAnnotation(BSyntaxes.class).value();
            }else if (getClass().isAnnotationPresent(BSyntax.class)){
                bindedSyntaxes = new BSyntax[]{getClass().getAnnotation(BSyntax.class)};
            }
            List<Map.Entry<String, String[]>> binds = new ArrayList<>();
            for (BSyntax bindedSyntax : bindedSyntaxes){
                for (String syntax : bindedSyntax.syntax()){
                    binds.add(new AbstractMap.SimpleEntry<String, String[]>(syntax, bindedSyntax.bind()));
                }
            }
            Map.Entry<String, String[]> bindUsed = binds.get(i);
            for (int i2 = 0; i2 < bindUsed.getValue().length; i2++){
                String bindName = bindUsed.getValue()[i2];
                if (indexExists(expressions, i2) || bindName.startsWith("-")){
                    if (indexExists(expressions, i2)) {
                        bindName = bindName.startsWith("-") ? bindName.substring(1) : bindName;
                        exp().bind(bindName, new UExpression(expressions[i2]));
                    }
                    //Nothing would happen if it didn't exist, and started with -.
                }else{
                    throw new BindedSyntaxException(String.format("Out of bounds binding error. bindName %s was #%d to be binded bu" +
                            "t there are only %d expressions. You can add an `-` to the beginning of a bindName for it to be able to be" +
                            "omitted without any error. IF YOU ARE NOT THE ADDON DEV, PLEASE REPORT THIS ERROR TO THE ADDON DEVELOPER OR" +
                            " ON FORUMS IF YOU CANT FIGURE OUT THE ADDON/MODULE FROM THIS ERROR", bindName, i2 + 1, expressions.length));
                }
            }
        }

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
    protected T[] get(Event event) {
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
                            toReturn = new Object[]{getEventValue(new EventSource(event))};
                            implemented = true;
                            break detect;
                        case "getEventValues":
                            toReturn = getEventValues(new EventSource(event));
                            implemented = true;
                            break detect;
                        case "getValue":
                            toReturn = new Object[]{getValue()};
                            implemented = true;
                            break detect;
                        case "getValues":
                            toReturn = getValues();
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



    public T getEventValue(EventSource eventSource){
        return null;
    }
    public T[] getEventValues(EventSource eventSource){
        return null;
    }
    public T getValue(){
        return null;
    }
    public T[] getValues(){
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
