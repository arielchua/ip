import java.util.Objects;
import java.util.Scanner;

public class ChuaChua {
    public static void main(String[] args) {
        //introduction
        String indent = "CC: ";
        String intro = indent + "Hi! I'm ChuaChua \n"
                + indent + "How can I help you?";
        System.out.println(intro);

        //echoing
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            System.out.println(indent + input);
            input = sc.nextLine();
        }

        //exit message
        String outro = indent + "Oh bye bye! See you soon!";
        System.out.println(outro);
    }
}
