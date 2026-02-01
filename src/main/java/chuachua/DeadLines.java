package chuachua;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLines extends Task{
    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private LocalDateTime deadline;

    public DeadLines(String name, String deadline) {
        super(name);
        this.deadline = LocalDateTime.parse(deadline, INPUT);
    }

    public DeadLines(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline.format(OUTPUT) + ")";
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toSaveString() {
        return "T | " + (status ? "1" : "0") + " | " + taskName + " | " + deadline;
    }

}
