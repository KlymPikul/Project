package UI;

import BL.TaskManagerByObjects;
import DL.Priority;
import DL.Status;
import DL.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleUiForObjects {
    private static final TaskManagerByObjects taskManager = new TaskManagerByObjects();
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
                    taskCount();
                    break;
                case 0:
                    return;
            }

        }
    }

    private static void displayTasks(){
        ArrayList<Task> tasks = taskManager.getTasks();
        tasks.forEach(System.out::println);
        }

        private static void addTask(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Укажите название задачи: ");
            String title = scanner.nextLine();
            System.out.printf("Укажите статус %s: %n", Arrays.toString(Status.values()));
            String status = scanner.nextLine();
            System.out.println("Укажите сложность задачи: ");
            int storyPoints = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("Укажите приоритет %s: %n", Arrays.toString(Priority.values()));
            String priority = scanner.nextLine();
            Task newTask = new Task(title, Status.get(status), storyPoints, Priority.get(priority));
            taskManager.addTask(newTask);
            System.out.println("Задача добавлена ");
        }

    private static void removeTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Укажите название или часть названия задачи для удаления: ");
        String substring = scanner.nextLine();
        taskManager.removeTask(substring);
        System.out.println("Задача удалена");
    }

    private static void taskCount() {
        System.out.println("Количество задач: " + taskManager.length());
    }

}

