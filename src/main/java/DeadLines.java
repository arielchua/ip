public class DeadLines extends Task{
    private String deadline;

    public DeadLines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }

    @Override
    public String toSaveString() {
        return "T | " + (status ? "1" : "0") + " | " + taskName + " | " + deadline;
    }

}
