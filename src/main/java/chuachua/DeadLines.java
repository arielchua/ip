package chuachua;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLines extends Task{
    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern(" MMM dd yyyy, h:mma");

    private final LocalDateTime deadline;

    public DeadLines(String name, String deadline) {
        super(name);
        this.deadline = LocalDateTime.parse(deadline, INPUT);
    }

    public DeadLines(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns String representation of DeadLine object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline.format(OUTPUT) + ")";
    }

    /**
     * Returns LocalDateTime for a DeadLine Object
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns String version of DeadLine object ot save onto disk
     */
    @Override
    public String toSaveString() {
        return "T | " + (isDone ? "1" : "0") + " | " + taskName + " | " + deadline;
    }

}
