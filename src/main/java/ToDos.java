public class ToDos extends Task{
    public ToDos (String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T | " + (status ? "1" : "0") + " | " + taskName;
    }
}
