package uk.co.umbaska.registrations;

/**
 * WIP : A class to wrap an {@link Enum} to avoid type conflicts
 * @author Andrew Tran
 */
public class WrappedEnum<T extends Enum<T>> {
    private T value;
    public WrappedEnum(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
