package uk.co.umbaska.registrations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that specifies that the {@link uk.co.umbaska.registrations.AutoRegisteringSkriptElement}
 * implements {@link uk.co.umbaska.registrations.DynamicSyntax} and prefers to use
 * that over annotations such as {@link Syntax}, {@link Syntaxes}, {@link BSyntax}, and {@link BSyntaxes}
 * @author Andrew Tran
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicSyntaxes {
}
