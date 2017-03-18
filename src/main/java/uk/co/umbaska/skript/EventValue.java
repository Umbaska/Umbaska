package uk.co.umbaska.skript;

import org.bukkit.event.Event;
import ch.njol.skript.util.Getter;
import java.lang.reflect.ParameterizedType;

/**
 * Represents a value that can be retrieved from an {@link Event}
 * @param <E> the Event to retrieve from
 * @param <V> the type of the value being retrieved
 * @author Andrew Tran
 */

public abstract class EventValue<E extends Event,V> extends Getter<V,E>{
    private Class<? extends Event> eventClass;
    private Class<?> valueClass;
    private EventTime eventTime;

    /**
     * Gets the time it was retrieved from an event
     * @return the time it was retrieved from an event
     */
    public EventTime getEventTime() {
        return eventTime;
    }

    /**
     * Sets the time it was retrieved from an event
     * @param eventTime the time to set
     */
    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Gets the class of the {@link Event}
     * @return the class of the Event
     */
    public Class<? extends Event> getEventClass(){
        if (eventClass == null){
            eventClass = (Class<E>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return eventClass;
    }

    /**
     * Gets the class of the value
     * @return the class of the value
     */
    public Class<?> getValueClass(){
        if (valueClass == null){
            valueClass = (Class<E>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return valueClass;
    }
}
