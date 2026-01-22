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
        String[] listItem = new String[100];
        boolean[] listBool = new boolean[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String output = printList(listItem, listBool, count);
                System.out.println(indent + output);
            } else if (input.startsWith("mark")) {
                int toMark = Integer.parseInt(input.substring(5));
                listBool[toMark] = true;                    //true = marked
                System.out.println(indent + "Great! I've marked this task as done:\n [X] " + listItem[toMark]);
            } else if (input.startsWith("unmark")) {
                int toUnMark = Integer.parseInt(input.substring(7));
                listBool[toUnMark] = false;                    //true = marked
                System.out.println(indent + "OK, I've marked this task as not done yet::\n [ ] " + listItem[toUnMark]);
            } else {
                count++;
                listItem[count] = input;
                listBool[count] = false;
                System.out.println(indent + "added: " + input);
            }
            input = sc.nextLine();
        }
        String outro = indent + "Oh bye bye! See you soon!";
        System.out.println(outro);

    }

    public static String printList(String[] ls, boolean[] bool, int count) {
        String output = "Here are the tasks in your list:\n";
        String marked = "[X] ";
        String unmarked = "[ ] ";
        for (int i = 1; i <= count; i++) {
            if (bool[i]) {
                output += i + ". " + marked + ls[i] + "\n";
            } else {
                output += i + ". " + unmarked + ls[i] + "\n";
            }
        }
        return output;
    }
}
