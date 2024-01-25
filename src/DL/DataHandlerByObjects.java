package DL;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


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
                        Status.valueOf(props[2]),
                        Integer.parseInt(props[3]),
                        Priority.valueOf(props[4])
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

        String resultString = String.join("\n", stringTasks);

        try (FileWriter writer = new FileWriter(this.file)) {
            writer.write(resultString);
        } catch (IOException e) {
            printError(e);
        }
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

    //     реализовать метод remove
//    данный метод получает в качестве аргумента id задачи и удаляет ее из файла
//    порядок выполнения
//    1) считать все задачи в ArrayList<Task>
//    2) сформировать новый List со всеми задачами, кроме удаляемой (добавить в Task getter  для id)
//    3) получивщийся List преобразовать в контект для файла (множество строк с данными о задачах в формате CSV)
//    4) записать данный контент в файл
}