package uk.co.umbaska.registrations;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.co.umbaska.Umbaska;
import uk.co.umbaska.registrations.annotations.Syntax;
import uk.co.umbaska.registrations.annotations.Syntaxes;
import uk.co.umbaska.skript.SimpleUmbaskaExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andrew Tran
 */
public class SyntaxLoader {
    public void load(Class<? extends AutoRegisteringSkriptElement> syntaxClass){
        String[] syntaxes = null;
        if (syntaxClass.isAnnotationPresent(Syntaxes.class)){
            List<String> syntaxesList = new ArrayList<>();
            Syntaxes syntaxesAnnotation = syntaxClass.getAnnotation(Syntaxes.class);
            for (Syntax syntaxAnnotation : syntaxesAnnotation.value()){
                for ()
            }
        }
        if (SimpleUmbaskaExpression.class.isAssignableFrom(syntaxClass)){
            try {
                SimpleUmbaskaExpression simpleUmbaskaExpression = (SimpleUmbaskaExpression) syntaxClass.newInstance();
                Skript.registerExpression(syntaxClass, simpleUmbaskaExpression.getReturnType(), ExpressionType.SIMPLE, );
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
