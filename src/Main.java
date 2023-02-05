import java.util.List;

public class Main {
    public static void main(String[] args) {
        //создал односложную задачу
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        Task buyKakadu = new Task("Купить какаду", "Выбрать доброго какаду", InMemoryTaskManager.getIdGenerator());
        inMemoryTaskManager.addTask(buyKakadu);
        //создал сложную задачу с 2 подзадачами
        Epic buyChairAndTable = new Epic("Купить стол и стул", "Подобрать обеденную группу",
                InMemoryTaskManager.getIdGenerator());
        inMemoryTaskManager.addEpic(buyChairAndTable);
        // подзадача №1
        Subtask buyChair = new Subtask("Купить стул", "Купить стул", InMemoryTaskManager.getIdGenerator(),
                buyChairAndTable.id);
        inMemoryTaskManager.addSubtask(buyChair);
        // подзадача №2
        Subtask buyTable = new Subtask("Купить стол", "Купить стол", InMemoryTaskManager.getIdGenerator(),
                buyChairAndTable.id);
        inMemoryTaskManager.addSubtask(buyTable);
        //создал сложную задачу с 1 подзадачей
        Epic buyСactus = new Epic("Купить кактус", "Больше никаких животных",
                InMemoryTaskManager.getIdGenerator());
        inMemoryTaskManager.addEpic(buyСactus);
        //подзадача №3
        Subtask pourСactus = new Subtask("Полить кактус", "Кактус хороший", InMemoryTaskManager.getIdGenerator(),
                buyСactus.id);
        inMemoryTaskManager.addSubtask(pourСactus);

        List<Task> history = Managers.getDefaultHistory().getHistory();

        //Просмотр истории первичный, чтобы убедиться, что там пусто
        System.out.println(history.size());
        for (Task printHistory : history) {
            System.out.println("Выводим историю: " + printHistory.toString());
        }

        /* Запись истории осуществляется 2 способами: через метод getTaskById() или через перезаписывание статуса
        viewAndSetStatus*/
        System.out.println(inMemoryTaskManager.getTaskById(3));
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.DONE, buyKakadu.id);
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.IN_PROGRESS, buyChair.id);
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.DONE, buyTable.id);
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.IN_PROGRESS, pourСactus.id);
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.DONE, buyChair.id);
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.DONE, buyTable.id);
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.DONE, pourСactus.id);
        inMemoryTaskManager.viewAndSetStatus(TaskStatus.DONE, 1);
        System.out.println(inMemoryTaskManager.getTaskById(1));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));
        System.out.println(inMemoryTaskManager.getTaskById(3));


        List<Task> historySec = Managers.getDefaultHistory().getHistory();
        for (Task printHistory :historySec) {
            System.out.println("Выводим историю: " + printHistory.toString());
        }
    }
}