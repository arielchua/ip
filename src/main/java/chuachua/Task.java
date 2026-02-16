package chuachua;

/**
 * Represents a generic task with a description and completion status.
 * Provides common behavior shared by all task types such as ToDos,
 * DeadLines, and Events.
 */
public abstract class Task {
    private boolean status;
    private String taskName;

    /**
     * Constructs a task with the specified name
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    /**
     * Marks a Task as done
     */
    public void mark() {
        this.status = true;
    }

    /**
     * Marks a Task as not done
     */
    public void unmark() {
        this.status = false;
    }

    /**
     * Prints task based on the completion status
     * @return
     */
    public String toString() {
        if (status) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    public abstract String toSaveString();
    public boolean getStatus() {
        return this.status;
    }

    public String getTaskName() {
        return this.taskName;
    }

}
