package uk.co.umbaska.skript;

/**
 * @author Andrew Tran
 */
public enum EventTime {
    BEFORE(-1), AFTER(1), NORMAL(0);
    int i;
    EventTime(int i){
        this.i = i;
    }
    public int getTime(){
        return i;
    }
}
