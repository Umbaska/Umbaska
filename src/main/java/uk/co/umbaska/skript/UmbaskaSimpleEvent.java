package uk.co.umbaska.skript;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.util.SimpleEvent;
import org.bukkit.event.Event;
import uk.co.umbaska.modules.UmbaskaModule;

/**
 * An Abstract Class that can be implemented to make a new {@link SimpleEvent}
 * @author Andrew Tran
 * @see UmbaskaModule#registerSyntaxes()
 */
public class UmbaskaSimpleEvent<T extends Event> extends UmbaskaEvent<T>{
    @Override
    public Class<? extends SkriptEvent> getSkriptEvent() {
        return SimpleEvent.class;
    }
}
