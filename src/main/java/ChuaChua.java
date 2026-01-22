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
        String[] list = new String[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String output = printList(list, count);
                System.out.println(indent + output);
            } else {
                count++;
                list[count] = input;
                System.out.println(indent + "added: " + input);
            }
            input = sc.nextLine();
        }
        String outro = indent + "Oh bye bye! See you soon!";
        System.out.println(outro);

    }

    public static String printList(String[] ls, int count) {
        String output = "";
        for (int i = 1; i <= count; i++) {
            output += i + ". " + ls[i] + "\n";
        }
        return output;
    }
}
