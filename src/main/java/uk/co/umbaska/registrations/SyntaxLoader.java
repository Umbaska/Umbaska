package uk.co.umbaska.registrations;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import com.google.common.math.BigIntegerMath;
import uk.co.umbaska.Umbaska;
import uk.co.umbaska.registrations.annotations.*;
import uk.co.umbaska.skript.SimpleUmbaskaExpression;
import uk.co.umbaska.skript.SimpleUmbaskaPropertyExpression;
import uk.co.umbaska.skript.UmbaskaEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andrew Tran
 */
public class SyntaxLoader {
    private int loadedExpressions = 0;
    private int loadedEffects;

    public void load(Class<? extends AutoRegisteringSkriptElement> syntaxClass){
        String[] syntaxes = null;
        if (syntaxClass.isAnnotationPresent(Syntaxes.class)){
            List<String> syntaxesList = new ArrayList<>();
            Syntaxes syntaxesAnnotation = syntaxClass.getAnnotation(Syntaxes.class);
            for (Syntax syntaxAnnotation : syntaxesAnnotation.value()){
                for (String syntax : syntaxAnnotation.value()){
                    syntaxesList.add(syntax);
                }
            }
            syntaxes = syntaxesList.toArray(new String[syntaxesList.size()]);
        }else if (syntaxClass.isAnnotationPresent(Syntax.class)){
            syntaxes = syntaxClass.getAnnotation(Syntax.class).value();
        }else if (syntaxClass.isAnnotationPresent(BSyntaxes.class)){
            List<String> syntaxesList = new ArrayList<>();
            BSyntaxes bSyntaxesAnnotation = syntaxClass.getAnnotation(BSyntaxes.class);
            for (BSyntax bSyntax : bSyntaxesAnnotation.value()){
                for (String syntax : bSyntax.syntax()){
                    syntaxesList.add(syntax);
                }
            }
            syntaxes = syntaxesList.toArray(new String[syntaxesList.size()]);
        }else if (syntaxClass.isAnnotationPresent(BSyntax.class)) {
            syntaxes = syntaxClass.getAnnotation(BSyntax.class).syntax();
        }else if (syntaxClass.isAnnotationPresent(DynamicSyntaxes.class)){
            try {
                String[] syntaxesDetected = ((DynamicSyntax) syntaxClass.newInstance()).getSyntax();
                if (syntaxesDetected == null){
                    Umbaska.getInstance().getLogger().severe(String.format("Class %s has the DynamicSyntaxes annotation but does not implement DynamicSyntaxes properly (got null)", syntaxClass.getCanonicalName()));
                    return;
                }
                syntaxes = syntaxesDetected;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }else{
            Umbaska.getInstance().getLogger().severe(String.format("Class %s does not have any of the annotations Syntaxes, Syntax, BSyntaxes, BSyntax, or DynamicSyntaxes", syntaxClass.getCanonicalName()));
            return;
        }
        if (SimpleUmbaskaExpression.class.isAssignableFrom(syntaxClass)){
            try {
                ExpressionType expressionType = ExpressionType.SIMPLE;
                if (syntaxClass.isAnnotationPresent(ExprType.class)){
                    expressionType = syntaxClass.getAnnotation(ExprType.class).value();
                }
                SimpleUmbaskaExpression simpleUmbaskaExpression = (SimpleUmbaskaExpression) syntaxClass.newInstance();
                Skript.registerExpression(simpleUmbaskaExpression.getClass(), simpleUmbaskaExpression.getReturnType(), expressionType, syntaxes);
                loadedExpressions++;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (SimpleUmbaskaPropertyExpression.class.isAssignableFrom(syntaxClass)){
            try {
                ExpressionType expressionType = ExpressionType.PROPERTY;
                if (syntaxClass.isAnnotationPresent(ExprType.class)){
                    expressionType = syntaxClass.getAnnotation(ExprType.class).value();
                }
                SimpleUmbaskaPropertyExpression simpleUmbaskaPropertyExpression = (SimpleUmbaskaPropertyExpression) syntaxClass.newInstance();

                if (syntaxes.length < 2){
                    throw new RuntimeException(String.format("Property Expression %s does not have two syntaxes which denote the property and the fromType (see docs)", syntaxClass.getCanonicalName()));
                }
                String property = syntaxes[0];
                String fromType = syntaxes[1];
                String[] propertySyntaxes = {
                    "[the] " + property + " of %" + fromType + "%", "%" + fromType + "%'[s] " + property
                };
                Skript.registerExpression(simpleUmbaskaPropertyExpression.getClass(), simpleUmbaskaPropertyExpression.getReturnType(), expressionType, propertySyntaxes);
                loadedExpressions++;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (UmbaskaEffect.class.isAssignableFrom(syntaxClass)){
            try {
                UmbaskaEffect umbaskaEffect = (UmbaskaEffect) syntaxClass.newInstance();
                Skript.registerEffect(umbaskaEffect.getClass(), syntaxes);
                loadedEffects++;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public int getLoadedExpressions() {
        return loadedExpressions;
    }

    public int getLoadedEffects() {
        return loadedEffects;
    }
}
