package chuachua;

import java.time.LocalDateTime;

public abstract class Task {
    public String taskName;
    public boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Gives a basic Task null as its deadline (no deadline)
     */
    public LocalDateTime getSortDateTime() {
        return null; // default: tasks without date/time (to do tasks)
    }

    /**
     * Marks a Task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a Task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    public abstract String toSaveString();

    public String getTaskName() {
        return this.taskName;
    }

}
