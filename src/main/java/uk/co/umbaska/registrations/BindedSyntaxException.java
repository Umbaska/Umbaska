package uk.co.umbaska.registrations;

/**
 * Thrown when something wrong happens with a Binded Syntax
 * @author Andrew Tran
 */
public class BindedSyntaxException extends RuntimeException{
    public BindedSyntaxException(String message) {
        super(message);
    }
}
