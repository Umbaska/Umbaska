package uk.co.umbaska.registrations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that is used to specify multiple {@link BSyntax}es for an {@link uk.co.umbaska.registrations.AutoRegisteringSkriptElement}
 * @author Andrew Tran
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BSyntaxes {
    /**
     * Gets the Binded Syntax annotations {@link BSyntax}
     * @return the Binded Syntax annotations
     */
    BSyntax[] value();
}
