package uk.co.umbaska.registrations;

import org.bukkit.event.Event;

/**
 * Represents a {@link Source} from an {@link Event}
 * @author Andrew Tran
 */
public class EventSource implements Source<Event>{
    private Event event;

    /**
     * Constructs an EventSource for an {@link Event}
     * @param event the event to construct an EventSource for
     */
    public EventSource(Event event) {
        this.event = event;
    }

    /**
     * Set the {@link Event}
     * @param event the event to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Gets the {@link Event}
     * @return the event
     */
    @Override
    public Event getSourceValue() {
        return event;
    }
}
