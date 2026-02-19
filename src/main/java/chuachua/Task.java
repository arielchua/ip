package chuachua;
public abstract class Task {
    public String taskName;
    public boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
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
