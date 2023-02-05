import java.util.*;

public class InMemoryTaskManager implements TaskManager  {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private static int idGenerator = 0;

    public static int getIdGenerator() {
        return idGenerator++;
    }

    //2.1 Получение списка всех задач.
    @Override
    public Map <Integer, ? extends Task> getAllTasks(TaskType taskType) {
        if (taskType.equals(TaskType.TASK)) {
            return  tasks;
        } else if (taskType.equals(TaskType.SUBTASK)) {
            return subtasks;
        } else {
            return epics;
        }
    }

    //2.2 Удаление всех задач.
    @Override
    public void removeAllTasks() {
        tasks.clear();
        subtasks.clear();
        epics.clear();
    }

    //2.3 Получение по идентификатору.
    @Override
    public Task getTaskById(int id) {
        Task result;
        if (tasks.containsKey(id)) {
            result = tasks.get(id);
        } else if (subtasks.containsKey(id)) {
            result = subtasks.get(id);
        } else {
            result = epics.get(id);
        }
        Managers.getDefaultHistory().add(result);//заполнение истории
        return result;
    }

    //2.4 Создание. Сам объект должен передаваться в качестве параметра.
    @Override
    public void addTask(Task task) {
        tasks.put(task.id, task);
    }

    //2.4 Создание. Сам объект должен передаваться в качестве параметра.
    @Override
    public void addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.epicId);
        if (epic == null) {
            System.out.println("Cоздана субзадача к несуществующему эпику!");
            return;
        }
        epic.addSubtaskId(subtask.id);
        subtasks.put(subtask.id, subtask);
    }

    //2.4 Создание. Сам объект должен передаваться в качестве параметра.
    @Override
    public void addEpic(Epic epic) {
        epics.put(epic.id, epic);
    }

    //2.5 Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    @Override
    public boolean update(Task newTask) {
        if (newTask instanceof Epic) {
            Task taskHashMap = removeTask(newTask.id);
            if (taskHashMap != null) {
                addEpic((Epic) newTask);
                return true;
            }
        } else if (newTask instanceof Subtask) {
            Task taskHashMap = removeTask(newTask.id);
            if (taskHashMap != null) {
                addSubtask((Subtask) newTask);
                return true;
            }
        } else {
            Task taskHashMap = removeTask(newTask.id);
            if (taskHashMap != null) {
                addTask(newTask);
                return true;
            }
        }
        return false;
    }

    //2.6 Удаление по идентификатору.

    /**
     * возвращается null, если task не был найден
     */
    @Override
    public Task removeTask(int id) {
        Task tempTask;
        if (tasks.containsKey(id)) {
            //Task tempTask = tasks.remove(id); сохранение удаленной из map в переменную для дальнейшего использования
            tempTask = tasks.remove(id);
        } else if (subtasks.containsKey(id)) {
            tempTask = subtasks.remove(id);
        } else {
            tempTask = epics.remove(id);
        }
        return tempTask;
    }

    //3.1 Получение списка всех подзадач определённого эпика.
    @Override
    public HashMap<Integer, Subtask> getSubtaskOfEpic(Epic epic) {
        HashMap<Integer, Subtask> subtasksOfEpic = new HashMap<>();
        for (int subtaskId : epic.subtasksIds) {
            Subtask subtaskOfEpic = subtasks.get(subtaskId);
            subtasksOfEpic.put(subtaskId, subtaskOfEpic);
        }
        return subtasksOfEpic;
    }

    //4 Управление статусами осуществляется по следующему правилу:
    @Override
    public void viewAndSetStatus(TaskStatus newStatus, int id) {
        Task updateTask = getTaskById(id);
        if (updateTask == null) {
            System.out.println("Ошибка: введен несуществующий id");
            return;
        }
        if (updateTask instanceof Subtask) {
            updateTask.setStatus(newStatus);
            int epicId = ((Subtask) updateTask).epicId;
            Epic epic = epics.get(epicId);
            if (epic == null) {
                throw new RuntimeException("Subtask ссылается на несуществующий Id Epic: " + epicId);
            }
            checkStatus(epic);
        } else if (updateTask instanceof Epic) {
            System.out.println("Нельзя принудительно присваивать статус EPIC");
        } else {
            updateTask.setStatus(newStatus);
        }
    }

    @Override
    public void checkStatus(Epic epic) {

        boolean statusFoundNew = false;
        boolean statusFoundProgress = false;
        boolean statusFoundDone = false;

        if (epic.subtasksIds.size() == 0) {
            epic.status = TaskStatus.NEW;
            return;
        }

        for (int subtaskId : epic.subtasksIds) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask.status.equals(TaskStatus.NEW)) {
                statusFoundNew = true;
            } else if (subtask.status.equals(TaskStatus.IN_PROGRESS)) {
                statusFoundProgress = true;
            } else {
                statusFoundDone = true;
            }

            if (statusFoundNew && !statusFoundProgress && !statusFoundDone) {
                epic.status = TaskStatus.NEW;
            } else if (!statusFoundNew && !statusFoundProgress && statusFoundDone) {
                epic.status = TaskStatus.DONE;
            } else {
                epic.status = TaskStatus.IN_PROGRESS;
            }
        }
    }

    public String toString() {
        String result = "";
        for (Epic epic : epics.values()) {
            result += epic.toString() + System.lineSeparator() + "сабтаски эпика: " + getSubtaskOfEpic(epic).values() +
                    System.lineSeparator();
        }
        return result;
    }
}