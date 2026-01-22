import java.util.Objects;
import java.util.Scanner;

public class ChuaChua {
    public static void main(String[] args) {
        //introduction
        String divider = "\n___________________________________\n";
        String intro = "Hi! I'm ChuaChua \n"
                + "How can I help you?"
                + divider;
              //  + "Bye. Hope to see you again soon!";
        System.out.println(intro);

        //echoing
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            System.out.println(input + divider);
            input = sc.nextLine();
        }

        //exit message
        String outro = "Oh bye bye! See you soon!";
        System.out.println(outro);
    }
}
