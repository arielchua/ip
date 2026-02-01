public class Events extends Task{
    public String start;
    public String end;

    public Events(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to:" + this.end + ")";
    }

    @Override
    public String toSaveString() {
        return "E | " + (status ? "1" : "0")
                + " | " + taskName
                + " | " + start
                + " | " + end;
    }
}
