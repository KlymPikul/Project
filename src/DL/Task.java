package DL;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class Task {
    private String path;
    private File file;

    public Task(String path) {
        this.path = path;
        this.file = new File(path);
    }

    // метод printError выводит ошибку
    private void printError(Exception e) {
        System.err.println("Error: " + e.getMessage());
    }

    // // Метод read, который возвращает ArrayList строк, прочитанных из файла
    public ArrayList<String> read() {
        ArrayList<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }

        } catch (Exception e) {
            printError(e);
        }

        return result;

    }

    // Метод write, который записывает новую строку в файл
    public void write(String string) {
        try (FileWriter writer = new FileWriter(this.file)) {
            writer.write(string);
        } catch (IOException e) {
            printError(e);
        }
    }

    public void writeNewLine(String string) {
        try (FileWriter writer = new FileWriter(this.file, true)) {
            writer.append("\n" + string);
        } catch (IOException e) {
            printError(e);
        }
    }

    // создать метод  remove  который получает индекс
    // строки в качестве аргумента и удаляет её.
    public void remove(int index) {
        try {
            ArrayList<String> rows = read();

            if (index >= 0 && index < rows.size()) {
                rows.remove(index);

                String content = String.join("\n", rows);

                write(content);
            } else {
                System.err.println("Error: Index out of bounds");
            }
        } catch (IndexOutOfBoundsException e) {
            printError(e);
        }
    }

    public ArrayList<Integer> search(String fragment) {
        ArrayList<Integer> findOfIndex = new ArrayList<>();
        try {
            ArrayList<String> rows = read();

            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).contains(fragment)) {
                    System.out.println("Фрагмент найден в строке с индексом: " + i);
                    findOfIndex.add(i);
                }
            }
            if (findOfIndex.isEmpty()) {
                System.out.println("Фрагмент не найден в файле.");
            }
        } catch (Exception e) {
            printError(e);
        }
        return findOfIndex;
    }
}




