import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> checkedTasks = new ArrayList<>();

    //должен помечать задачи как просмотренные
    public void add(Task task) {
        if (checkedTasks.size() > 10) {
            checkedTasks.remove(0);
        }
        checkedTasks.add(task);
    }

    public List<Task> getHistory() {
        return checkedTasks;
    }
}
