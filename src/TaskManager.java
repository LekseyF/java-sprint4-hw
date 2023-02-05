import java.util.Map;

public interface TaskManager {

    //2.1 Получение списка всех задач.
     Map<Integer, ? extends Task> getAllTasks(TaskType taskType);
    //2.2 Удаление всех задач.
     void removeAllTasks();

    //2.3 Получение по идентификатору.
    Task getTaskById(int id);

    //2.4 Создание таска. Сам объект должен передаваться в качестве параметра.
    void addTask(Task task);

    //2.4 Создание сабтаска. Сам объект должен передаваться в качестве параметра.
    void addSubtask(Subtask subtask);

    //2.4 Создание эпика. Сам объект должен передаваться в качестве параметра.
    void addEpic(Epic epic);

    //2.5 Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    boolean update(Task newTask);

    //2.6 Удаление по идентификатору.

    Task removeTask(int id);

    //3.1 Получение списка всех подзадач определённого эпика.
    Map<Integer, Subtask> getSubtaskOfEpic(Epic epic);

    //4 Управление статусами осуществляется по следующему правилу:
    void viewAndSetStatus(TaskStatus newStatus, int id);

    void checkStatus(Epic epic);


}
