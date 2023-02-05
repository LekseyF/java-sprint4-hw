import java.util.ArrayList;
import java.util.Arrays;

public class Epic extends Task {

    protected ArrayList<Integer> subtasksIds = new ArrayList<>();


    public Epic(String name, String description, int id) {
        super(name, description, id);
    }

    @Override
    public void setStatus(TaskStatus status) {
        System.out.println("Нельзя принудительно присваивать статус EPIC");
    }

    public void addSubtaskId(Integer subtaskId) {
        subtasksIds.add(subtaskId);
    }

    @Override
    public String toString() {
        return super.toString() + ", subtasksId = " + Arrays.toString(subtasksIds.toArray());
    }
}