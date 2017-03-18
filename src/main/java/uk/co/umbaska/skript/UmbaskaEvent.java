package uk.co.umbaska.skript;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.SkriptEvent;
import org.bukkit.event.Event;
import uk.co.umbaska.modules.UmbaskaModule;
import uk.co.umbaska.registrations.AutoRegisteringSkriptElement;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * An Abstract Class that can be implemented to make a new {@link SkriptEvent}
 * @author Andrew Tran
 * @see UmbaskaModule#registerSyntaxes()
 */
public abstract class UmbaskaEvent<T extends Event> implements AutoRegisteringSkriptElement{
    private Class<? extends Event> eventClass;
    private List<EventValue> eventValues = new ArrayList<>();

    public Class<? extends Event> getEventClass(){
        if (eventClass == null){
            eventClass = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return eventClass;
    }

    public abstract Class<? extends SkriptEvent> getSkriptEvent();

    public void registerEventValue(EventValue<T,?> eventValue){
        registerEventValue(eventValue, EventTime.NORMAL);
    }

    public void registerEventValue(EventValue<T,?> eventValue, EventTime eventTime){
        eventValue.setEventTime(eventTime);
        eventValues.add(eventValue);
    }

    public EventValue<T,?>[] getEventValues(){
        return eventValues.toArray(new EventValue[eventValues.size()]);
    }

    public String getName(){
        return getEventClass().getSimpleName().replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }
}
