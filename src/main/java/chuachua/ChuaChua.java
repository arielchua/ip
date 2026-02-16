package chuachua;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The main entry point of the chuachua application.
 * Manages user interaction, task storage, and command execution.
 */
public class ChuaChua {

    private static final String FILE_PATH = "./data/chua.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a ChuaChua instance using the specified file path for storage
     * Initialises a user interface, storage component, and task list by loading
     * previously saved tasks from given file
     * @param filePath
     */
    public ChuaChua(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        // If your Storage.load() already handles missing file + returns empty list,
        // this will always work.
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot and decides the response based on command
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();
                String[] cmd = Parser.parse(input);
                String type = cmd[0];

                if (type.equals("bye")) {
                    ui.showBye();
                    return;
                }

                switch (type) {

                case "list":
                    ui.showMessage(tasks.toNumberedList());
                    break;

                case "todo": {
                    Task t = new ToDos(cmd[1]);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Okay, I've added the task:\n  "
                            + t + "\nNow you have " + tasks.size() + " tasks in the list");
                    break;
                }

                case "deadline": {
                    // cmd: ["deadline", description, byRaw]
                    Task t = new DeadLines(cmd[1], cmd[2]);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Okay, I've added the task:\n  "
                            + t + "\nNow you have " + tasks.size() + " tasks in the list");
                    break;
                }

                case "event": {
                    // cmd: ["event", description, startRaw, endRaw]
                    Task t = new Events(cmd[1], cmd[2], cmd[3]);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Okay, I've added the task:\n  "
                            + t + "\nNow you have " + tasks.size() + " tasks in the list");
                    break;
                }

                case "mark": {
                    int idx = Integer.parseInt(cmd[1]) - 1;
                    Task t = tasks.mark(idx);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Great! I've marked this task as done:\n" + t);
                    break;
                }

                case "unmark": {
                    int idx = Integer.parseInt(cmd[1]) - 1;
                    Task t = tasks.unmark(idx);
                    storage.save(tasks.getTasks());
                    ui.showMessage("OK, I've marked this task as not done yet:\n" + t);
                    break;
                }

                case "delete": {
                    int idx = Integer.parseInt(cmd[1]) - 1;
                    Task removed = tasks.delete(idx);
                    storage.save(tasks.getTasks());
                    ui.showMessage("I've removed this task:\n"
                            + removed + "\nNow you have " + tasks.size() + " tasks in the list");
                    break;
                }

                case "find": {
                    String keyword = cmd[1];
                    ArrayList<Task> matches = tasks.findTasks(keyword);
                    ui.showFoundTasks(matches);
                    break;
                }


                default:
                    ui.showError("Sorry I don't know what you mean :(");
                    break;
                }

            } catch (EmptyDescriptionException e) {
                ui.showError(e.getMessage());
            } catch (UnknownCommandException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                ui.showError("That task number doesn't exist.");
            } catch (DateTimeParseException e) {
                ui.showError("Please use date format yyyy-MM-dd HHmm (e.g. 2019-12-02 1800).");
            }
        }
    }

    public static void main(String[] args) {
        new ChuaChua(FILE_PATH).run();
    }
}
