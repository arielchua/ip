package chuachua;
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

    /**
     * Parses user input and returns a String array:
     * cmd[0] = command word
     * cmd[1..] = arguments
     */
    public static String[] parse(String input)
            throws EmptyDescriptionException, UnknownCommandException {

        assert input != null : "User input should not be null";

        input = input.trim();

        if (input.equals("bye")) {
            return new String[]{"bye"};
        }

        if (input.equals("list")) {
            return new String[]{"list"};
        }

        // ----- mark / unmark / delete -----
        if (input.equals("mark") || input.startsWith("mark ")) {
            String num = input.length() <= 4 ? "" : input.substring(5).trim();
            if (num.isEmpty()) {
                throw new EmptyDescriptionException("Please include the task number!");
            }
            return new String[]{"mark", num};
        }

        if (input.equals("unmark") || input.startsWith("unmark ")) {
            String num = input.length() <= 6 ? "" : input.substring(7).trim();
            if (num.isEmpty()) {
                throw new EmptyDescriptionException("Please include the task number!");
            }
            return new String[]{"unmark", num};
        }

        if (input.equals("delete") || input.startsWith("delete ")) {
            String num = input.length() <= 6 ? "" : input.substring(7).trim();
            if (num.isEmpty()) {
                throw new EmptyDescriptionException("Please include the task number!");
            }
            return new String[]{"delete", num};
        }

        // ----- todo -----
        if (input.startsWith("todo")) {
            String desc = input.substring(4).trim();
            if (desc.isEmpty()) {
                throw new EmptyDescriptionException(
                        "Please include the task description for me!");
            }
            return new String[]{"todo", desc};
        }

        // ----- deadline -----
        if (input.startsWith("deadline")) {
            String rest = input.substring(8).trim();
            String[] parts = rest.split(" /by ", 2);
            if (parts.length < 2) {
                throw new EmptyDescriptionException(
                        "Please include /by for deadline!");
            }
            return new String[]{"deadline", parts[0].trim(), parts[1].trim()};
        }

        // ----- event -----
        if (input.startsWith("event")) {
            String rest = input.substring(5).trim();
            String[] first = rest.split(" /from ", 2);
            if (first.length < 2) {
                throw new EmptyDescriptionException(
                        "Please include /from and /to for event!");
            }

            String[] second = first[1].split(" /to ", 2);
            if (second.length < 2) {
                throw new EmptyDescriptionException(
                        "Please include /to for event!");
            }

            return new String[]{
                    "event",
                    first[0].trim(),
                    second[0].trim(),
                    second[1].trim()
            };
        }

        // ----- find -----
        if (input.startsWith("find")) {
            String rest = input.substring(5).trim();
            if (rest.isEmpty()) {
                throw new EmptyDescriptionException("Please provide the task you are finding!");
            }
            return new String[]{"find", rest};
        }

        // ----- unknown command -----
        throw new UnknownCommandException(
                "Sorry I don't know what you mean :("
        );
    }
}