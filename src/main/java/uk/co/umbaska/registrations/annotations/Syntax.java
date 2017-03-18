package uk.co.umbaska.registrations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that specifies the {@link ch.njol.skript.Skript} syntaxes to be used
 * @author Andrew Tran
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Syntax {
    /**
     * Gets the {@link ch.njol.skript.Skript} syntaxes to be registered
     * @return the {@link ch.njol.skript.Skript} syntaxes to be registered
     */
    String[] value();
}
