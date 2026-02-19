package chuachua;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {

    private final ArrayList<Task> tasks;
    private static final Comparator<Task> TASK_ORDER =
            Comparator.comparing(Task::getSortDateTime,
                            Comparator.nullsFirst(Comparator.naturalOrder()))
                    .thenComparing(Task::getTaskName);


    /**
     * Sorts the task in order of TASK_ORDER
     */
    private void sortTasks() {
        tasks.sort(TASK_ORDER);
    }

    /**
     * Creates a task list from existing list from storage
     * - If loaded from storage
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Loaded task list should not be null";
        this.tasks = tasks;
        sortTasks();
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
        assert task != null : "Cannot add null task";
        tasks.add(task);
        sortTasks();
    }

    /** Deletes and returns the removed task (0-based index). */
    public Task delete(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in delete";
        return tasks.remove(index);
    }

    /** Marks a task done (0-based index) and returns the updated task. */
    public Task mark(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in mark";
        Task t = tasks.get(index);
        t.mark();
        return t;
    }

    /** Marks a task not done (0-based index) and returns the updated task. */
    public Task unmark(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in unmark";
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

    /**
     * Returns ArrayList<Task> containing the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Search keyword should not be null";
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }


}
