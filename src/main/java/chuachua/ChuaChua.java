package chuachua;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ChuaChua {

    private static final String FILE_PATH = "./data/chua.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final AiHelper aiHelper = new AiHelper();


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
                    ui.showMessage("Okay, I've added the task:\n  " + t
                            + "\nNow you have " + tasks.size() + " tasks in the list");
                    break;
                }

                case "deadline": {
                    // cmd: ["deadline", description, byRaw]
                    Task t = new DeadLines(cmd[1], cmd[2]);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Okay, I've added the task:\n  " + t
                            + "\nNow you have " + tasks.size() + " tasks in the list");
                    break;
                }

                case "event": {
                    // cmd: ["event", description, startRaw, endRaw]
                    Task t = new Events(cmd[1], cmd[2], cmd[3]);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showMessage("Okay, I've added the task:\n  " + t
                            + "\nNow you have " + tasks.size() + " tasks in the list");
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
                    ui.showMessage("I've removed this task:\n" + removed
                            + "\nNow you have " + tasks.size() + " tasks in the list");
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

    //GUI version of run so that it returns the line instead of printng it only
    public String getResponse(String input) {
        try {
            String[] cmd = Parser.parse(input);
            String type = cmd[0];

            if (type.equals("bye")) {
                return "Bye! Hope to see you again soon!";
            }

            switch (type) {

            case "list":
                return tasks.toNumberedList();

            case "todo": {
                Task t = new ToDos(cmd[1]);
                tasks.add(t);
                storage.save(tasks.getTasks());
                return "Okay, I've added the task:\n  " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list";
            }

            case "deadline": {
                Task t = new DeadLines(cmd[1], cmd[2]);
                tasks.add(t);
                storage.save(tasks.getTasks());
                return "Okay, I've added the task:\n  " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list";
            }

            case "event": {
                Task t = new Events(cmd[1], cmd[2], cmd[3]);
                tasks.add(t);
                storage.save(tasks.getTasks());
                return "Okay, I've added the task:\n  " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list";
            }

            case "mark": {
                int idx = Integer.parseInt(cmd[1]) - 1;
                Task t = tasks.mark(idx);
                storage.save(tasks.getTasks());
                return "Great! I've marked this task as done:\n" + t;
            }

            case "unmark": {
                int idx = Integer.parseInt(cmd[1]) - 1;
                Task t = tasks.unmark(idx);
                storage.save(tasks.getTasks());
                return "OK, I've marked this task as not done yet:\n" + t;
            }

            case "delete": {
                int idx = Integer.parseInt(cmd[1]) - 1;
                Task removed = tasks.delete(idx);
                storage.save(tasks.getTasks());
                return "I've removed this task:\n" + removed
                        + "\nNow you have " + tasks.size() + " tasks in the list";
            }

            case "find": {
                String keyword = cmd[1];
                ArrayList<Task> matches = tasks.findTasks(keyword);
                TaskList temp = new TaskList(matches);
                return temp.toFilteredList(); // adjust if needed
            }

            case "@ai": {
                try {
                    return askAiAboutFeature(cmd[1], aiHelper);
                } catch (Exception e) {
                    e.printStackTrace(); // TEMP: see error in console
                    return "AI error: " + e.getClass().getSimpleName() + " - " + e.getMessage();
                }
            }

            default:
                return "Sorry I don't know what you mean :(";
            }

        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        } catch (UnknownCommandException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Please enter a valid task number.";
        } catch (IndexOutOfBoundsException e) {
            return "That task number doesn't exist.";
        } catch (DateTimeParseException e) {
            return "Please use date format d/M/yyyy HHmm (e.g. 26/09/2005 1800).";
        }

    }

    /**
     * Helps generate an AI help response
     */
    private static String askAiAboutFeature(String userPrompt, AiHelper aiHelper) {
        String systemPrompt =
                "You are helping users of a CLI task manager app.\n"
                        + "Answer the user's query about the app's commands and features.\n"
                        + "Keep the response short (1-2 sentences) and specific.\n"
                        + "If the feature does not exist, say so and suggest the closest alternative.\n\n"
                        + "Supported commands:\n"
                        + "1. todo <description>\n"
                        + "2. deadline <description> /by <d/M/yyyy HHmm>\n"
                        + "3. event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>\n"
                        + "4. list\n"
                        + "5. mark <task_number>\n"
                        + "6. unmark <task_number>\n"
                        + "7. delete <task_number>\n"
                        + "8. find <keyword>\n";

        return aiHelper.getAiResponse(systemPrompt, userPrompt).aiMessage().text();
    }



    public static void main(String[] args) {
        new ChuaChua(FILE_PATH).run();
    }
}