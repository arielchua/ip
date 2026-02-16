package chuachua;

/**
 * Signals that the user entered a command that is not recognized
 * by the application.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}
