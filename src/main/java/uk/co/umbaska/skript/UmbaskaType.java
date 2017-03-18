package uk.co.umbaska.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.lang.Condition;
import uk.co.umbaska.modules.UmbaskaModule;
import uk.co.umbaska.registrations.AutoRegisteringSkriptElement;

/**
 * An Abstract Class that can be implemented to make a new {@link ch.njol.skript.Skript} class (type)
 * @author Andrew Tran
 * @see UmbaskaModule#registerSyntaxes()
 */
public abstract class UmbaskaType<T> implements AutoRegisteringSkriptElement{
    public abstract ClassInfo<T> getClassInfo();
}
