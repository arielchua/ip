import java.util.ArrayList;
import java.util.Scanner;

public class ChuaChua {
    public static void main(String[] args) {
        //introduction
        String indent = "CC:---------------------------------------\n";
        String intro = indent + "Hi! I'm ChuaChua\n"
                      + "How can I help you?";
        System.out.println(intro);

//        //add list
//        Task[] tasks = new Task[100];
//        int count = 0;

        Scanner sc = new Scanner(System.in);
        //change to arraylist
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            String input = sc.nextLine();

            try {
                validate(input);

                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    String output = printList(tasks);
                    System.out.println(indent + output);

                } else if (input.startsWith("mark")) {
                    int toMark = Integer.parseInt(input.substring(5));
                    tasks.get(toMark).mark();
                    System.out.println(indent + "Great! I've marked this task as done:\n" + tasks.get(toMark));

                } else if (input.startsWith("unmark")) {
                    int toUnMark = Integer.parseInt(input.substring(7));
                    tasks.get(toUnMark).unmark();
                    System.out.println(indent + "OK, I've marked this task as not done yet:\n" + tasks.get(toUnMark));

                } else if (input.startsWith("todo")) {
                    tasks.add(new ToDos(input.substring(5)));
                    System.out.println(indent + adding(tasks.get(tasks.size() - 1), tasks));

                } else if (input.startsWith("deadline")) {
                    String rest = input.substring(9);
                    String[] parts = rest.split(" /by ", 2);
                    tasks.add(new DeadLines(parts[0], parts[1]));
                    System.out.println(indent + adding(tasks.get(tasks.size() - 1), tasks));

                } else if (input.startsWith("event")) {
                    String rest = input.substring(6);
                    String[] firstSplit = rest.split(" /from ", 2);
                    String[] secondSplit = firstSplit[1].split(" /to ", 2);
                    tasks.add(new Events(firstSplit[0], secondSplit[0], secondSplit[1]));
                    System.out.println(indent + adding(tasks.get(tasks.size() - 1), tasks));
                }

            } catch (EmptyDescriptionException e) {
                System.out.println(indent + e.getMessage());
            } catch (UnknownCommandException e) {
                System.out.println(indent + e.getMessage());
            }
        }


        String outro = indent + "Oh bye bye! See you soon!";
        System.out.println(outro);

    }

    public static String printList(ArrayList<Task> tasks) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            output += i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        output += "Now you have " + tasks.size() + " tasks in the list";
        return output;
    }

    public static String adding(Task task, ArrayList<Task> tasks) {
        String output = "Okay, I've added the task:\n  "
                + task.toString()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list";
        return output;
    }
    public static void validate(String input)
            throws EmptyDescriptionException, UnknownCommandException {

        if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
            throw new EmptyDescriptionException(
                    "Please include the task description for me!"
            );
        }

        if (!(input.startsWith("todo")
                || input.startsWith("deadline")
                || input.startsWith("event")
                || input.equals("list")
                || input.equals("bye"))) {

            throw new UnknownCommandException(
                    "Sorry I don't know what you mean :("
            );
        }
    }

}
