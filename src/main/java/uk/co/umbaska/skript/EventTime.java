package uk.co.umbaska.skript;

/**
 * Represents what time an event happened (past, right now, or future)
 * @author Andrew Tran
 */
public enum EventTime {
    BEFORE(-1), AFTER(1), NORMAL(0);
    int i;
    EventTime(int i){
        this.i = i;
    }

    /**
     * Gets the {@link ch.njol.skript.Skript} time for an EventTime
     * @return the Skript time for an EventTime
     */
    public int getTime(){
        return i;
    }
}
