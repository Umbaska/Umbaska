package uk.co.umbaska.registrations;

import org.bukkit.event.Event;

/**
 * @author Andrew Tran
 */
public class EventSource implements Source<Event>{
    Event event;

    public EventSource(Event event) {
        this.event = event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public Event getSourceValue() {
        return event;
    }
}
