package uk.co.umbaska.registrations;

/**
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
