import javax.swing.*;
import java.util.Scanner;

public class Ui {

    private static final String INDENT = "CC:---------------------------------------\n";
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println(INDENT + "Hi! I'm ChuaChua\nHow can I help you?");
    }

    /**
     * Prints bye message
     */
    public void showBye() {
        System.out.println(INDENT + "Oh bye bye! See you soon!");
    }

    /**
     * Reads the command as a string
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a message as a String
     */
    public void showMessage(String message) {
        System.out.println(INDENT + message);
    }

    public void showError(String message) {
        System.out.println(INDENT + message);
    }

}
