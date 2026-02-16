package chuachua;

/**
 * Represents a to-do task without a specific date or time.
 */
public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String version of ToDos object ot save onto disk
     */
    @Override
    public String toSaveString() {
        return "T | " + (getStatus() ? "1" : "0") + " | " + getTaskName();
    }
}
