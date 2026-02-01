import java.time.format.DateTimeParseException;

public class ChuaChua {

    private static final String FILE_PATH = "./data/chua.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public ChuaChua(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        // If your Storage.load() already handles missing file + returns empty list,
        // this will always work.
        tasks = new TaskList(storage.load());
    }

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

//
//public class ChuaChua {
//    public static void main(String[] args) {
//        //introduction
//        String indent = "CC:---------------------------------------\n";
//        String intro = indent + "Hi! I'm ChuaChua\n"
//                      + "How can I help you?";
//        System.out.println(intro);
//
//        Scanner sc = new Scanner(System.in);
//
//        Storage storage = new Storage("./data/chua.txt");
//        ArrayList<Task> tasks = storage.load();
//
//        while (true) {
//            String input = sc.nextLine();
//
//            try {
//                validate(input);
//
//                if (input.equals("bye")) {
//                    break;
//                } else if (input.equals("list")) {
//                    String output = printList(tasks);
//                    System.out.println(indent + output);
//
//                } else if (input.startsWith("mark")) {
//                    int toMark = Integer.parseInt(input.substring(5)) - 1;
//                    tasks.get(toMark).mark();
//                    storage.save(tasks);
//                    System.out.println(indent + "Great! I've marked this task as done:\n" + tasks.get(toMark));
//
//                } else if (input.startsWith("unmark")) {
//                    int toUnMark = Integer.parseInt(input.substring(7)) - 1;
//                    tasks.get(toUnMark).unmark();
//                    storage.save(tasks);
//                    System.out.println(indent + "OK, I've marked this task as not done yet:\n" + tasks.get(toUnMark));
//
//                } else if (input.startsWith("todo")) {
//                    tasks.add(new ToDos(input.substring(5)));
//                    storage.save(tasks);
//                    System.out.println(indent + adding(tasks.get(tasks.size() - 1), tasks));
//
//                } else if (input.startsWith("deadline")) {
//                    String rest = input.substring(9);
//                    String[] parts = rest.split(" /by ", 2);
//                    tasks.add(new DeadLines(parts[0], parts[1]));
//                    storage.save(tasks);
//                    System.out.println(indent + adding(tasks.get(tasks.size() - 1), tasks));
//
//                } else if (input.startsWith("event")) {
//                    String rest = input.substring(6);
//                    String[] firstSplit = rest.split(" /from ", 2);
//                    String[] secondSplit = firstSplit[1].split(" /to ", 2);
//                    tasks.add(new Events(firstSplit[0], secondSplit[0], secondSplit[1]));
//                    storage.save(tasks);
//                    System.out.println(indent + adding(tasks.get(tasks.size() - 1), tasks));
//                } else if (input.startsWith("delete")) {
//                    int toDelete = Integer.parseInt(input.substring(7)) - 1; // "delete " is 7 chars
//                    Task removed = tasks.remove(toDelete);
//                    storage.save(tasks);
//                    System.out.println(indent + "I've removed this task:\n" + removed);
//                    System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list");
//                }
//
//            } catch (EmptyDescriptionException e) {
//                System.out.println(indent + e.getMessage());
//            } catch (UnknownCommandException e) {
//                System.out.println(indent + e.getMessage());
//            }
//        }
//
//
//        String outro = indent + "Oh bye bye! See you soon!";
//        System.out.println(outro);
//
//    }
//
//    public static String printList(ArrayList<Task> tasks) {
//        String output = "Here are the tasks in your list:\n";
//        for (int i = 1; i <= tasks.size(); i++) {
//            output += i + ". " + tasks.get(i - 1).toString() + "\n";
//        }
//        output += "Now you have " + tasks.size() + " tasks in the list";
//        return output;
//    }
//
//    public static String adding(Task task, ArrayList<Task> tasks) {
//        String output = "Okay, I've added the task:\n  "
//                + task.toString()
//                + "\nNow you have "
//                + tasks.size()
//                + " tasks in the list";
//        return output;
//    }
//    public static void validate(String input)
//            throws EmptyDescriptionException, UnknownCommandException {
//
//        if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
//            throw new EmptyDescriptionException(
//                    "Please include the task description for me!"
//            );
//        }
//
//        if (!(input.startsWith("todo")
//                || input.startsWith("deadline")
//                || input.startsWith("event")
//                || input.startsWith("mark")
//                || input.startsWith("unmark")
//                || input.startsWith("delete")
//                || input.equals("list")
//                || input.equals("bye"))) {
//
//            throw new UnknownCommandException(
//                    "Sorry I don't know what you mean :("
//            );
//        }
//    }
//
//}
