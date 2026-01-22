import java.util.Objects;
import java.util.Scanner;

public class ChuaChua {
    public static void main(String[] args) {
        //introduction
        String indent = "CC:---------------------------------------\n";
        String intro = indent + "Hi! I'm ChuaChua \n"
                      + "How can I help you?";
        System.out.println(intro);

        //add list
        Task[] tasks = new Task[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String output = printList(tasks, count);
                System.out.println(indent + output);
            } else if (input.startsWith("mark")) {
                int toMark = Integer.parseInt(input.substring(5));
                tasks[toMark].mark();
                System.out.println(indent + "Great! I've marked this task as done:\n" + tasks[toMark]);
            } else if (input.startsWith("unmark")) {
                int toUnMark = Integer.parseInt(input.substring(7));
                tasks[toUnMark].unmark();
                System.out.println(indent + "OK, I've marked this task as not done yet:\n" + tasks[toUnMark]);
            } else {
                count++;
                tasks[count] = new Task(input);
                System.out.println(indent + "added: " + input);
            }
            input = sc.nextLine();
        }

        String outro = indent + "Oh bye bye! See you soon!";
        System.out.println(outro);

    }

    public static String printList(Task[] tasks, int count) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 1; i <= count; i++) {
            output += i + ". " + tasks[i].toString() + "\n";
        }
        return output;
    }
}
