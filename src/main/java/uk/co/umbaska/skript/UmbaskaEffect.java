package uk.co.umbaska.skript;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.co.umbaska.registrations.*;
import uk.co.umbaska.registrations.annotations.BSyntax;
import uk.co.umbaska.registrations.annotations.BSyntaxes;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

/**
 * @author Andrew Tran
 */
public abstract class UmbaskaEffect extends Effect implements AutoRegisteringSkriptElement, ExpressionManagerProvider, DynamicSyntax{
    private ExpressionManager expressionManager;

    @Override
    public String[] getSyntax() {
        return null;
    }

    @Override
    public String toString(Event event, boolean b) {
        return String.format("Umbaska Effect %s", getClass().getCanonicalName());
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
                        System.out.println("BINDED " + bindName);
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
    protected void execute(Event event) {
        if (isUsingExpressionManager()){
            exp().setEvent(event);
        }
        Boolean implemented = false;
        detect: {
            for (Method method : getClass().getMethods()) {
                if (method.getDeclaringClass().equals(getClass())) {
                    switch (method.getName()) {
                        case "execute":
                            execute();
                            implemented = true;
                            break detect;
                        case "getEventValues":
                            executeWithEvent(new EventSource(event));
                            implemented = true;
                            break detect;
                    }
                }
            }
        }
        if (!implemented){
            throw new RuntimeException(String.format("Class %s does not implement either execute, or executeWithEvent.", getClass().getCanonicalName()));
        }
    }

    public void execute() {};
    public void executeWithEvent(EventSource eventSource) {};

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
