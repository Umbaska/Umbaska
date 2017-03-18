package uk.co.umbaska.registrations.annotations;

import java.lang.annotation.*;

/**
 * Annotation that is used for specifying a Binded Syntax for an {@link uk.co.umbaska.registrations.AutoRegisteringSkriptElement}
 * @author Andrew Tran
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BSyntax {
    /**
     * Gets the {@link ch.njol.skript.Skript} syntaxes
     * @return the {@link ch.njol.skript.Skript} syntaxes
     */
    String[] syntax();

    /**
     * Gets the bind names (e.g {@code "text"})
     * @return the bind names
     */
    String[] bind();
}
