package chuachua;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{

    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public LocalDateTime start;
    public LocalDateTime end;

    public Events(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Events(String name, String startRaw, String endRaw) {
        super(name);
        this.start = LocalDateTime.parse(startRaw.trim(), INPUT);
        this.end = LocalDateTime.parse(endRaw.trim(), INPUT);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(OUTPUT) + " to:" + this.end.format(OUTPUT) + ")";
    }

    @Override
    public String toSaveString() {
        return "E | " + (status ? "1" : "0")
                + " | " + taskName
                + " | " + start.toString()
                + " | " + end.toString();
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}
