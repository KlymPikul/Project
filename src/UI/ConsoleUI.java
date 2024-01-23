package UI;

import BL.TaskManager;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {
    private static final TaskManager taskManager = new TaskManager();
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите опцию");
            System.out.println("1. Вывести задачи");
            System.out.println("2. Добавить задачу");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Количество задач");
            System.out.println("5. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    displayTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    displayTaskCount();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }

        }
    }
    private static void displayTasks() {
        ArrayList<String> tasks = taskManager.getTasks();
        //System.out.println("Список задач:");
        for (String task : tasks) {
            System.out.println(task);
        }
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите описание задачи:");
        String description = scanner.nextLine();
        taskManager.addTask(description);
        System.out.println("Задача добавлена успешно.");
    }

    private static void removeTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите номер задачу для удаления:");
        String number = scanner.nextLine();
        taskManager.removeTask(number);
        System.out.println("Задача удалена успешно.");

    }

    private static void displayTaskCount() {
        System.out.println(taskManager.length());
    }


}
