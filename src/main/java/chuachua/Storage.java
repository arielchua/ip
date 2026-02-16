package chuachua;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving of task data to the local file system.
 * Responsible for reading tasks from a data file at startup and
 * writing updated task information back to disk.
 */
public class Storage {
    public final Path filePath;

    public Storage(String relativePath) {
        this.filePath = Paths.get(relativePath);
    }

    /**
     * Loads tasks from disk into memory
     * - If the file doesn't exist yet, create the folder + file and return an empty list
     * - If the file exists, read all the lines and convert each lien into a Task object
     */
    public ArrayList<Task> load() {
        try {
            ensureFileExists();

            List<String> lines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                //else convert into Task object
                Task task = Parser.parseSavedTask(line);
                tasks.add(task);
            }

            return tasks;
        } catch (Exception e) {
            //returns empty list
            return new ArrayList<Task>();
        }
    }

    /**
     * Saves tasks from memory onto disk
     * Re-writes the entire task list each time
     */
    public void save(ArrayList<Task> tasks) {
        try {
            ensureFileExists();
            ;

            ArrayList<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(task.toSaveString());
            }

            Files.write(filePath, lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Unable to save tasks. Sorry!");
        }
    }

    /**
     * Ensures ./data exists and the save file exists.
     * Example: ./data/chua.txt
     */
    private void ensureFileExists() throws IOException {
        Path parent = filePath.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath); // creates ./data/chua.txt
        }
    }

}
