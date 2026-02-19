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

        assert input != null : "Input should not be null";

        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new EmptyDescriptionException("Please include a command!");
        }

        //split the input into 2 parts
        String[] headTail = trimmed.split(" ", 2);
        String command = headTail[0];
        String args = headTail.length == 2 ? headTail[1].trim() : "";

        switch (command) {
        case "bye":
        case "list":
            requireNoArgs(command, args);
            return new String[]{command};

        case "mark":
        case "unmark":
        case "delete":
            return parseIndexCommand(command, args);

        case "todo":
            return parseTodo(args);
        case "deadline":
            return parseDeadline(args);
        case "event":
            return parseEvent(args);
        case "find":
            return parseFind(args);

        default:
            throw new UnknownCommandException("Sorry I don't know what you mean :(");
        }
    }

    //helper methods for parser
    private static void requireNoArgs(String command, String args) throws EmptyDescriptionException {
        if (!args.isEmpty()) {
            throw new EmptyDescriptionException("Command '" + command + "' does not take arguments.");
        }
    }

    private static String[] parseIndexCommand(String command, String args) throws EmptyDescriptionException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("Please include the task number!");
        }
        return new String[]{command, args};
    }

    private static String[] parseTodo(String args) throws EmptyDescriptionException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("Please include the task description for me!");
        }
        return new String[]{"todo", args};
    }

    private static String[] parseDeadline(String args) throws EmptyDescriptionException {
        String[] parts = args.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new EmptyDescriptionException("Please include: deadline <desc> /by <time>");
        }
        return new String[]{"deadline", parts[0].trim(), parts[1].trim()};
    }

    private static String[] parseEvent(String args) throws EmptyDescriptionException {
        String[] first = args.split(" /from ", 2);
        if (first.length < 2 || first[0].trim().isEmpty()) {
            throw new EmptyDescriptionException("Please include: event <desc> /from <start> /to <end>");
        }

        String[] second = first[1].split(" /to ", 2);
        if (second.length < 2 || second[0].trim().isEmpty() || second[1].trim().isEmpty()) {
            throw new EmptyDescriptionException("Please include: event <desc> /from <start> /to <end>");
        }

        return new String[]{"event", first[0].trim(), second[0].trim(), second[1].trim()};
    }

    private static String[] parseFind(String args) throws EmptyDescriptionException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("Please provide the task you are finding!");
        }
        return new String[]{"find", args};
    }

}