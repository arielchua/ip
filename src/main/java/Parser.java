import java.time.LocalDate;

public class Parser {

    public static Task parseSavedTask(String line) {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
            case "T":
                task = new ToDos(description);
                break;

            case "D":
                task = new DeadLines(description, parts[3]);
                break;

            case "E":
                task = new Events(description, parts[3], parts[4]);
                break;

            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.mark();
        }

        return task;
    }
}