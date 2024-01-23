package UI;

import DL.Task;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Task task = new Task("src/data.txt");
        task.remove(0);
        task.write("№1 Первая задача");
        task.writeNewLine("№2 Вторая задача");
        task.writeNewLine("№3 Вторая задача");
        task.remove(1);
        System.out.println(task.read());

        ArrayList<Integer> findOfIndex = task.search("задача");
        System.out.println(findOfIndex);





    }
}
