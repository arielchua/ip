import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates a task list from existing list from storage
     * - If loaded from storage
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a new empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Returns number of tasks. */
    public int size() {
        return tasks.size();
    }

    /** Adds a task. */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Deletes and returns the removed task (0-based index). */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /** Marks a task done (0-based index) and returns the updated task. */
    public Task mark(int index) {
        Task t = tasks.get(index);
        t.mark();
        return t;
    }

    /** Marks a task not done (0-based index) and returns the updated task. */
    public Task unmark(int index) {
        Task t = tasks.get(index);
        t.unmark();
        return t;
    }

    /** Returns a numbered list string for Ui to display. */
    public String toNumberedList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list");
        return sb.toString();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
