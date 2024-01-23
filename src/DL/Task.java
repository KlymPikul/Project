package DL;

public class Task {
    private static int idCounter = 1;
    private int id;
    private String title;
    private Status status;
    private int storyPoints;
    private Priority priority;

    public Task(String title, Status status, int storyPoints, Priority priority) {
        this.id = idCounter++;
        this.title = title;
        this.status = status;
        setStoryPoints(storyPoints);
        this.priority = priority;
    }


    private void setStoryPoints(int v) {
        // Проверяем сложность задачи
        this.storyPoints = (v >= 1 && v <= 10) ? v : 10;
    }


}

/*
id - число
Title - строка
Status - enum (DONE, IN_PROGRESS, REJECTED, NEW)
StoryPoints (сложность задачи) - int (от 1 до 10), если пользователь ввёл
что-то другое, то 10)
priority - enum (LOW< MEDIUM, HIGH)
 */