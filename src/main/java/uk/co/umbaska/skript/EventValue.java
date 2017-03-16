package uk.co.umbaska.skript;

import org.bukkit.event.Event;
import ch.njol.skript.util.Getter;
import java.lang.reflect.ParameterizedType;

/**
 * @author Andrew Tran
 */
public abstract class EventValue<E extends Event,V> extends Getter<V,E>{
    private Class<? extends Event> eventClass;
    private Class<?> valueClass;
    private EventTime eventTime;

    public EventTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    public Class<? extends Event> getEventClass(){
        if (eventClass == null){
            eventClass = (Class<E>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return eventClass;
    }
    public Class<?> getValueClass(){
        if (valueClass == null){
            valueClass = (Class<E>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return valueClass;
    }
}
