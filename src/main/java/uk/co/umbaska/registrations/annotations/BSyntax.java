package uk.co.umbaska.registrations.annotations;

import java.lang.annotation.*;

/**
 * @author Andrew Tran
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BSyntax {
    String[] syntax();
    String bind();
}
