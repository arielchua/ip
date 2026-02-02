package chuachua;
public abstract class Task {
    public String taskName;
    public boolean status;

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    public void mark() {
        this.status = true;
    }

    public void unmark() {
        this.status = false;
    }

    public String toString() {
        if (status) {
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
