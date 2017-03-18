package uk.co.umbaska.registrations.annotations;

import ch.njol.skript.lang.ExpressionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that specifies the {@link ExpressionType} to be used when registering this {@link uk.co.umbaska.skript.UmbaskaExpression}
 * @author Andrew Tran
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExprType {
    ExpressionType value();
}
