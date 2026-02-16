package chuachua;
/**
 * Signals that a required task description or argument
 * was not provided by the user.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
