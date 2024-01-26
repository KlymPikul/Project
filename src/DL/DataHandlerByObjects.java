package DL;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.io.IOException;


public class DataHandlerByObjects {
    private String path;
    private File file;

    public DataHandlerByObjects(String path) {
        this.path = path;
        this.file = new File(path);
    }

    private void printError(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    public ArrayList<Task> read() {
        ArrayList<Task> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                String newTask = scanner.nextLine();
                String[] props = newTask.split(",");
                System.out.println(Arrays.toString(props));
                Task task = new Task(
                        Integer.parseInt(props[0]),
                        props[1],
                        Status.get(props[2]),
                        Integer.parseInt(props[3]),
                        Priority.get(props[4])
                );
                result.add(task);
            }
        } catch (Exception e) {
            printError(e);
        }
        return result;
    }

    public void write(Task task) {
        ArrayList<Task> tasks = read();
        tasks.add(task);

        List<String> stringTasks = tasks
                .stream()
                .map(x -> x.getCSV())
                .collect(Collectors.toList());

        writeArrayToFile(stringTasks);
    }

    public void remove(int id) {
        ArrayList<Task> tasks = read();
        List<String> result = tasks.stream()
                .filter(x -> x.getId() != id)
                .map(x -> x.getCSV())
                .collect(Collectors.toList());

        String resultString = String.join("\n", result);

        try (FileWriter writer = new FileWriter(this.file)) {
            writer.write(resultString);
        } catch (IOException e) {
            printError(e);
        }
    }

    private void writeArrayToFile(List<String> array) {
        String resultString = String.join("\n", array);

        try (FileWriter writer = new FileWriter(this.file)) {
            writer.write(resultString);
        } catch (IOException e) {
            printError(e);
        }
    }

    public int search(String substring) {
        ArrayList<Task> tasks = read();
        Optional<Task> result = tasks.stream()
                .filter(x -> x.getTitle().contains(substring))
                .findFirst();
        if (!result.isEmpty()) {
            return -1;
        } else {
            return result.get().getId();
        }
    }


}

