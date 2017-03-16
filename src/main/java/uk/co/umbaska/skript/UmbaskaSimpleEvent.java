package uk.co.umbaska.skript;

import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.util.SimpleEvent;
import org.bukkit.event.Event;

/**
 * @author Andrew Tran
 */
public class UmbaskaSimpleEvent<T extends Event> extends UmbaskaEvent<T>{
    @Override
    public Class<? extends SkriptEvent> getSkriptEvent() {
        return SimpleEvent.class;
    }
}
