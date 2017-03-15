package uk.co.umbaska.skript;

import ch.njol.skript.classes.ClassInfo;
import uk.co.umbaska.registrations.AutoRegisteringSkriptElement;

/**
 * @author Andrew Tran
 */
public abstract class UmbaskaType<T> implements AutoRegisteringSkriptElement{
    public abstract ClassInfo<T> getClassInfo();
}
