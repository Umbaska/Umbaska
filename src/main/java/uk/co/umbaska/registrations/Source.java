package uk.co.umbaska.registrations;

/**
 * Represents a source from <b>somewhere</b>
 * @author Andrew Tran
 */
public interface Source<T> {
    public T getSourceValue();
}
