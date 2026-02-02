package chuachua;
import java.util.ArrayList;
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

    /**
     * Prints a list of all tasks that matches description when using command "find"
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("Found what you're looking for?");
    }


}
